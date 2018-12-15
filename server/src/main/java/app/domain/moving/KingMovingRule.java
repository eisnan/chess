package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;

import java.util.List;

public class KingMovingRule implements MovingRule {
    @Override
    public List<Position> getPossiblePositions(Piece piece, Position currentPosition) {
        return null;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return null;
    }

    @Override
    public List<Position> removeInvalidPositions(MoveType moveType, Position currentPosition, Piece piece, List<Position> positions) {
        return null;
    }
}
