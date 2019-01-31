package app.domain.moving.validators;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.moves.Move;
import app.domain.moving.MoveSettings;

import java.util.*;

public class RBQValidator implements PositionValidator {

    @Override
    public Collection<Position> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<Position>> possiblePositions) {
        List<Position> validPositions = new ArrayList<>();
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            validPositions.addAll(keepValid(chessBoard, selectedPiece, positions));
        });
        return validPositions;
    }

    @Override
    public Collection<Position> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<Position>> possiblePositions) {
        return keepValidPositionsToMove(chessBoard, moveSettings, possiblePositions);
    }

    private Collection<Position> keepValid(ChessBoard chessBoard, Piece selectedPiece, Collection<Position> positions) {
        List<Position> validPositions = new ArrayList<>();
        for (Position position : positions) {
            Piece piece = chessBoard.getModel().get(position);
            if (piece == null) {
                validPositions.add(position);
            } else if (selectedPiece.getPieceColor() != piece.getPieceColor()) {
                validPositions.add(position);
                break;
            } else {
                break;
            }
        }
        return validPositions;
    }
}
