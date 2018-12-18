package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public interface MoveDescriber {

    Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);


}
