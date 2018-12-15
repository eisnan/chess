package app.domain;

import java.util.List;

public interface MovingRule {
    List<Position> getPossiblePositions(Piece piece, Position currentPosition);

    MoveSettings getMoveSettings(Position currentPosition, Piece piece);

    List<Position> removeInvalidPositions(MoveType moveType, Piece piece, List<Position> positions);
}
