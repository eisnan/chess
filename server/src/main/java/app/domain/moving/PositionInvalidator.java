package app.domain.moving;

import app.domain.Position;

import java.util.List;

public interface PositionInvalidator {

    List<Position> invalidatePositions();
}
