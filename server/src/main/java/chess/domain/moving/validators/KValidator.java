package chess.domain.moving.validators;

import chess.domain.*;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;
import chess.domain.util.Pair;
import com.google.common.eventbus.Subscribe;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Gabs on 1/31/2019.
 * scope prototype
 */
public class KValidator implements PositionValidator {

    private AttackResolver attackResolver = new AttackResolver();
    private CheckEvaluator checkEvaluator = new CheckEvaluator();

    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        List<PlayerMove> validPositions = new ArrayList<>();
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            validPositions.addAll(keepValid(chessBoard, selectedPiece, positions));
        });
        return validPositions;

    }

    @Override
    public Collection<PlayerMove> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        Collection<PlayerMove> validPositionsToAttack = PositionValidator.super.keepValidPositionsToAttack(chessBoard, moveSettings, possiblePositions);
        return validPositionsToAttack.stream().peek(playerMove -> {
            if (playerMove.getPiece().getPieceColor().isPromotionRank(playerMove.getToPosition().getRank())) {
//                playerMove.setMoveType(MoveType.PROMOTION);
            }
        }).collect(Collectors.toSet());
    }

    /**
     * King cannot move to:
     * - be in check
     * - be close to enemy king
     *
     * @param chessBoard
     * @param selectedPiece
     * @param playerMoves
     * @return
     */
    private Collection<PlayerMove> keepValid(ChessBoard chessBoard, Piece selectedPiece, Set<PlayerMove> playerMoves) {
        Collection<PlayerMove> validMoves = new ArrayList<>();
        for (PlayerMove playerMove : playerMoves) {
            if (MoveType.isCastling(playerMove.getMoveType())) {
                validMoves.addAll(computeCastling(chessBoard, playerMove));
            } else {
                Piece piece = chessBoard.getModel().get(playerMove.getToPosition());
                if (piece == null) {
                    if (!checkEvaluator.isCheckedMove(chessBoard, playerMove)) {
                        validMoves.add(playerMove);
                    }
                } else if (selectedPiece.getPieceColor() != piece.getPieceColor() && kingsAreNotAdjacent(chessBoard, playerMove)) {
                    validMoves.add(playerMove);
                    break;
                } else {
                    break;
                }
            }

        }

        return validMoves;
    }

    private boolean kingsAreNotAdjacent(ChessBoard chessBoard, PlayerMove playerMove) {
        return true;
    }


    /*
    No pieces between the king and rook(s) whatsoever & none of the positions king is moving through are in check
     */
    private Collection<PlayerMove> computeCastling(ChessBoard chessBoard, PlayerMove playerMove) {
        Collection<PlayerMove> castlingMoves = new ArrayList<>();
        PieceColor pieceColor = playerMove.getPiece().getPieceColor();
        if (playerMove.getMoveType() == MoveType.KING_SIDE_CASTLING) {
            switch (pieceColor) {
                case WHITE:
                    Collection<Pair<Position, Piece>> attackingPieces = attackResolver.whoIsAttackingPosition(chessBoard, pieceColor, Position.of("e1"), Position.of("f1"), Position.of("g1"));
                    if (attackingPieces.isEmpty()) {
                        PlayerMove kingCastling = PlayerMove.of(Piece.getWhitePiece(PieceType.KING), Position.of("e1"), Position.of("g1"), MoveType.KING_SIDE_CASTLING);
                        PlayerMove rookCastling = PlayerMove.of(Piece.getWhitePiece(PieceType.ROOK), Position.of("h1"), Position.of("f1"), MoveType.KING_SIDE_CASTLING);
                        castlingMoves.add(kingCastling);
                        castlingMoves.add(rookCastling);
                    }

                    break;
                case BLACK:
//                    Collection<Pair<Position, Piece>> attackingCastlingPositions = attackResolver.whoIsAttackingPosition(chessBoard, Position.of("e1"), Position.of("f1"), Position.of("g1"));
                    break;
            }
        }

        if (playerMove.getMoveType() == MoveType.QUEEN_SIDE_CASTLING) {

        }
        return castlingMoves;
    }


    @Subscribe
    public void castlingEvent(String event) {
        System.out.println("event triggered" + event);
    }
}
