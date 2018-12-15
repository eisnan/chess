package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;

import java.util.List;

public interface Movable {

    List<Position> getAvailablePositions(Piece piece);

}
