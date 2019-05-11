package chess.domain.start;

import chess.domain.Piece;
import chess.domain.Position;

import java.util.Map;
import java.util.Set;

public interface StartPositionResolver {

    Map<Piece, Set<Position>> getInitialPositions();

}
