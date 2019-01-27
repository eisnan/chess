package app.domain.moving.validators;

import app.domain.*;
import app.domain.moving.*;
import app.domain.moving.moves.*;

import java.util.*;
import java.util.stream.Collectors;

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
