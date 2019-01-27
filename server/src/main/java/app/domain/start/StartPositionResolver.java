package app.domain.start;

import app.domain.Piece;
import app.domain.Position;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StartPositionResolver {

    Map<Piece, Set<Position>> getInitialPositions();

}
