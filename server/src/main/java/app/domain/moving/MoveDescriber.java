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

    Collection<MoveDescriber> STRAIGHT_MOVE_DESCRIBERS = Lists.newArrayList(new ForwardMove(), new BackwardMove(), new LeftMove(), new RightMove());
    Collection<MoveDescriber> DIAGONAL_MOVE_DESCRIBERS = Lists.newArrayList(new ForwardDiagonalLeft(), new ForwardDiagonalRight(), new BackwardDiagonalLeft(), new BackwardDiagonalRight());
    Collection<MoveDescriber> ALL_MOVE_DESCRIBERS = Lists.newArrayList(Iterables.concat(STRAIGHT_MOVE_DESCRIBERS, DIAGONAL_MOVE_DESCRIBERS));

    Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);

    BiFunction<Integer, Integer, Integer> fileFunction();

    BiFunction<Integer, Integer, Integer> rankFunction();

    Comparator<Position> getPositionComparator();
}
