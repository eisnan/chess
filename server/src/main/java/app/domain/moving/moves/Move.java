package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveSettings;

import java.util.Set;
import java.util.SortedSet;

public interface Move {

    Set<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);


}
