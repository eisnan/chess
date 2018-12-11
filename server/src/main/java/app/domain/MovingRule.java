package app.domain;

import java.util.List;

public interface MovingRule {
    List<Position> getPossiblePositions(Piece piece, Position currentPosition);

    MoveSettings getMoveSettings(Position currentPosition, PieceColor pieceColor);
}
