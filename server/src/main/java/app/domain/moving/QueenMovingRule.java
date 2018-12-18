package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.Collection;
import java.util.List;

public class QueenMovingRule implements MovingRule {
    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return null;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return null;
    }

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece piece, Collection<Position> positions) {
        return null;
    }
}
