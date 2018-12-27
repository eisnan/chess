package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RBQValidator implements PositionValidator {

    @Override
    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        List<Position> validPositions = new ArrayList<>();
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            validPositions.addAll(keepValid(chessBoard, selectedPiece, positions));
        });
        return validPositions;
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
