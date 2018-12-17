package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;

import java.util.List;

public class PInvalidator implements PositionInvalidator {
    @Override
    public List<Position> invalidate(Position currentPosition, Piece selectedPiece, List<Position> positions) {
        return null;
    }

    @Override
    public List<Position> invalidate(MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions) {
        return null;
    }
}
