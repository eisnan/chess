package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class RBQInvalidator implements PositionInvalidator {
    @Override
    public List<Position> invalidate(ChessBoard chessBoard,Position currentPosition, Piece selectedPiece, List<Position> positions) {
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

    @Override
    public List<Position> invalidate(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions) {
        throw new RuntimeException();
    }
}
