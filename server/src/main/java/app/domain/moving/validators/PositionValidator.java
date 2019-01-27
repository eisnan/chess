package app.domain.moving.validators;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;

import java.util.Collection;
import java.util.Map;

public interface PositionValidator {

    Collection<Position> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions);
    Collection<Position> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions);
}
