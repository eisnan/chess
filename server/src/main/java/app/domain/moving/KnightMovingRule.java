package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.Collection;
import java.util.List;

public class KnightMovingRule implements MovingRule {
    @Override
    public List<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return null;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return null;
    }

    @Override
    public List<Position> removeInvalidPositions(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece piece, Collection<Position> positions) {
        return null;
    }
}
