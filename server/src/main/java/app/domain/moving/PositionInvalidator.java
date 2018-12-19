package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.Collection;
import java.util.List;

public interface PositionInvalidator {

    Collection<Position> invalidate(ChessBoard chessBoard,Position currentPosition, Piece selectedPiece, Collection<Position> positions);

    Collection<Position> invalidate(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions);
}
