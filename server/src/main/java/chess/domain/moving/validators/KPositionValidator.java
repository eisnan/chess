package chess.domain.moving.validators;

import chess.domain.*;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;
import chess.domain.util.Pair;
import com.google.common.eventbus.Subscribe;

import java.util.*;

/**
 * Created by Gabs on 1/31/2019.
 * scope prototype
 */
public class KPositionValidator implements PositionValidator {

    private AttackResolver attackResolver = new AttackResolver();

    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        List<PlayerMove> validPositions = new ArrayList<>();
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            validPositions.addAll(keepValid(chessBoard, selectedPiece, positions));
        });
        return validPositions;

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

        for (PlayerMove playerMove : playerMoves) {
            if (MoveType.isCastling(playerMove.getMoveType())) {
                validateCastling(chessBoard, playerMove);
            }
        }

        return null;
    }

    /*
    No pieces between the king and rook(s) whatsoever & none of the positions king is moving through are in check
     */
    private boolean validateCastling(ChessBoard chessBoard, PlayerMove playerMove) {
//        Collection<Pair<Position, Piece>> attackingCastlingPositions = new ArrayList<>();
        if (playerMove.getMoveType() == MoveType.KING_SIDE_CASTLING) {
            switch (playerMove.getPiece().getPieceColor()) {
                case WHITE:
                    boolean attackingCastlingPositions = attackResolver.whoIsAttackingPosition(chessBoard, Position.of("e1"), Position.of("f1"), Position.of("g1")).stream().findAny().isPresent();


                    break;
                case BLACK:
//                    Collection<Pair<Position, Piece>> attackingCastlingPositions = attackResolver.whoIsAttackingPosition(chessBoard, Position.of("e1"), Position.of("f1"), Position.of("g1"));
                    break;
            }
        }

        if (playerMove.getMoveType() == MoveType.QUEEN_SIDE_CASTLING) {

        }
        return false;
    }

    @Override
    public Collection<PlayerMove> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        return null;
    }

    @Subscribe
    public void castlingEvent(String event) {
        System.out.println("event triggered" + event);
    }
}
