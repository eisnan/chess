package chess.domain.moving.validators;

import chess.domain.*;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;

import java.util.*;
import java.util.stream.Collectors;

public class PawnValidator implements PositionValidator {

    private GameRulesValidator gameRulesValidator = new GameRulesValidator();

    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        gameRulesValidator.pawnCanMoveOnlyToOneDirection(possiblePositions);

        Collection<PlayerMove> validToMove = new HashSet<>();
        Position currentPosition = moveSettings.getCurrentPosition();
        boolean firstRankForColor = moveSettings.getPiece().getPieceColor().isFirstRank(currentPosition.getRank());

        possiblePositions.forEach((moveDescriber, playerMoves) -> {
            for (PlayerMove playerMove : playerMoves) {
                if (chessBoard.q.isEmpty(playerMove.getToPosition())) {
                    validToMove.add(playerMove);
                    if (!firstRankForColor) {
                        break;
                    }
                } else {
                    break;
                }
            }
        });
        return validToMove.stream().map(playerMove -> new PlayerMove(playerMove, MoveType.MOVE)).collect(Collectors.toSet());
    }

    @Override
    public Collection<PlayerMove> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        Collection<PlayerMove> validPositionsToAttack = PositionValidator.super.keepValidPositionsToAttack(chessBoard, moveSettings, possiblePositions);
        possiblePositions.values().stream().flatMap(Set::stream).filter(playerMove -> isEnPassant(chessBoard, moveSettings.getPiece(), moveSettings.getCurrentPosition(), playerMove.getToPosition()))
                .findFirst().ifPresent(validPositionsToAttack::add);
        return validPositionsToAttack;
    }

    public boolean isEnPassant(ChessBoard chessBoard, Piece currentPiece, Position currentPosition, Position evaluatedPosition) {
        PlayerMove lastPlayerMove;
        boolean lastMoveMadeByPawn;
        boolean lastMoveWasDoubleStep;
        boolean sameFiles;
        switch (currentPiece.getPieceColor()) {
            case WHITE:

                lastPlayerMove = chessBoard.getLastMove();
                if (lastPlayerMove != null) {
                    lastMoveMadeByPawn = lastPlayerMove.getPiece().getPieceType() == PieceType.PAWN;
                    lastMoveWasDoubleStep = lastPlayerMove.getFromPosition().getRank().ordinal() - lastPlayerMove.getToPosition().getRank().ordinal() > 1;
                    sameFiles = lastPlayerMove.getToPosition().getFile() == evaluatedPosition.getFile();
                    return currentPosition.getRank() == Rank._5 && lastMoveMadeByPawn && lastMoveWasDoubleStep && sameFiles;
                }
                break;
            case BLACK:
                lastPlayerMove = chessBoard.getLastMove();
                if (lastPlayerMove != null) {
                    lastMoveMadeByPawn = lastPlayerMove.getPiece().getPieceType() == PieceType.PAWN;
                    lastMoveWasDoubleStep = lastPlayerMove.getFromPosition().getRank().ordinal() - lastPlayerMove.getToPosition().getRank().ordinal() < -1;
                    sameFiles = lastPlayerMove.getToPosition().getFile() == evaluatedPosition.getFile();
                    return currentPosition.getRank() == Rank._4 && lastMoveMadeByPawn && lastMoveWasDoubleStep && sameFiles;
                }
                break;
        }
        return false;
    }
}
