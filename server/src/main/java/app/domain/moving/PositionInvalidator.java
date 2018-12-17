package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;

import java.util.List;

public interface PositionInvalidator {

    List<Position> invalidate(Position currentPosition, Piece selectedPiece, List<Position> positions);

    List<Position> invalidate(MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions);
}
