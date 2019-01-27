package app.domain.moving.validators;

import app.domain.*;
import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerMove;
import app.domain.moving.moves.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;

public class PawnValidator implements PositionValidator {

    private GameRulesValidator gameRulesValidator = new GameRulesValidator();

    @Override
    public Collection<Position> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, SortedSet<Position>> possiblePositions) {
        gameRulesValidator.pawnCanMoveOnlyToOneDirection(possiblePositions);

        Collection<Position> validToMove = new ArrayList<>();
        Position currentPosition = moveSettings.getCurrentPosition();
        boolean firstRankForColor = moveSettings.getPiece().getPieceColor().isFirstRank(currentPosition.getRank());

        possiblePositions.forEach((moveDescriber, positions) -> {
            for (Position position : positions) {
                if (chessBoard.isEmpty(position)) {
                    validToMove.add(position);
                    if (!firstRankForColor) {
                        break;
                    }
                } else {
                    break;
                }
            }
        });


        return validToMove;
    }

    @Override
    public Collection<Position> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, SortedSet<Position>> possiblePositions) {
        Collection<Position> validPositionsToAttack = PositionValidator.super.keepValidPositionsToAttack(chessBoard, moveSettings, possiblePositions);
        possiblePositions.values().stream().flatMap(SortedSet::stream).filter(position -> isEnPassant(chessBoard, moveSettings.getPiece(), moveSettings.getCurrentPosition(), position))
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
