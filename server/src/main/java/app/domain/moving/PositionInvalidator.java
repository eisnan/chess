package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.List;

public interface PositionInvalidator {

    List<Position> invalidate(ChessBoard chessBoard,Position currentPosition, Piece selectedPiece, List<Position> positions);

    List<Position> invalidate(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions);
}
