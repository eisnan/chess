package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.moves.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.BiFunction;

public interface MoveDescriber {

    Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);

    BiFunction<Integer, Integer, Integer> fileFunction();

    BiFunction<Integer, Integer, Integer> rankFunction();

    Comparator<Position> getPositionComparator();
}
