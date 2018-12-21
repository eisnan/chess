package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;

import java.util.Collection;
import java.util.Map;

public interface PositionValidator {

    Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions);
}
