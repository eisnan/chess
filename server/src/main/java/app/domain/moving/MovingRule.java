package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.Collection;
import java.util.List;

public interface MovingRule {
    Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition);

    MoveSettings getMoveSettings(Position currentPosition, Piece piece);

    Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece selectedPiece, Collection<Position> positions);
}
