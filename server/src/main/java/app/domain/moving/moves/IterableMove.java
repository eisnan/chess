package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveSettings;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.BiFunction;

/**
 * Created by Gabs on 1/31/2019.
 */
public interface IterableMove extends Move {

    @Override
    SortedSet<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);

    BiFunction<Integer, Integer, Integer> fileFunction();

    BiFunction<Integer, Integer, Integer> rankFunction();

    Comparator<Position> getPositionComparator();
}
