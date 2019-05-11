package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.DirectionIterator;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.function.BiFunction;

public class BackwardDiagonalLeft implements IterableMove {
    private Comparator<PlayerMove> positionComparator = new DescendingPositionComparator();
    private DirectionIterator directionIterator = new DirectionIterator();

    @Override
    public SortedSet<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return directionIterator.iterate(moveSettings, this, fileFunction() , rankFunction());
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return (integer, integer2) -> integer - integer2;
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
