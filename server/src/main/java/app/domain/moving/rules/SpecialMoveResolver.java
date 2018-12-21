package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveSettings;

import java.util.Collection;

public interface SpecialMoveResolver {
    Collection<Position> validate(ChessBoard chessBoard, MoveSettings moveSettings, PieceColor pieceColor, Collection<Position> positions);
}
