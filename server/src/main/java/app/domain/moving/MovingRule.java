package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.List;

public interface MovingRule {
    List<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition);

    MoveSettings getMoveSettings(Position currentPosition, Piece piece);

    List<Position> removeInvalidPositions(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions);
}
