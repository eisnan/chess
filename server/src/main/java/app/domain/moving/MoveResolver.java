package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.PieceColor;
import app.domain.Position;

import java.util.Collection;

public interface MoveResolver {
    Collection<Position> validate(ChessBoard chessBoard, MoveDescriber moveDescriber, MoveSettings moveSettings, PieceColor pieceColor, Collection<Position> positions);
}
