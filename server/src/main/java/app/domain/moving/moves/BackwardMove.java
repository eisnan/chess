package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.comparators.DescendingPositionComparator;
import app.domain.moving.DirectionIterator;
import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.function.BiFunction;

public class BackwardMove implements IterableMove {
    private Comparator<PlayerMove> positionComparator = new DescendingPositionComparator();
    private DirectionIterator directionIterator = new DirectionIterator();

    @Override
    public SortedSet<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return directionIterator.iterate(moveSettings, this, fileFunction(), rankFunction());
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return (integer, integer2) -> integer;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return (integer, integer2) -> integer - integer2;
    }

    @Override
    public Comparator<PlayerMove> getPositionComparator() {
        return positionComparator;
    }


    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
