package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiFunction;

public interface MoveDescriber {

    Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);

    BiFunction<Integer, Integer, Integer> fileFunction();

    BiFunction<Integer, Integer, Integer> rankFunction();

}
