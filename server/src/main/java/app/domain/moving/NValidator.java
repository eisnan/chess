package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;

import java.util.Collection;
import java.util.Map;

public class NValidator implements PositionValidator {
    @Override
    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        return null;
    }
}
