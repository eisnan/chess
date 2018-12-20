package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RBQInvalidator implements PositionInvalidator {
//    @Override
    public Collection<Position> invalidate(ChessBoard chessBoard, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        List<Position> validPositions = new ArrayList<>();
        for (Position position : positions) {
            Piece piece = chessBoard.getModel().get(position);
            if (piece == null) {
                validPositions.add(position);
            } else if (selectedPiece.getPieceColor() != piece.getPieceColor() && piece.canBeCaptured()) {
                validPositions.add(position);
                break;
            } else {
                break;
            }
        }
        return validPositions;
    }

//    @Override
    public Collection<Position> invalidate(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        throw new RuntimeException();
    }

    @Override
    public List<Position> invalidatePositions() {
        return null;
    }
}
