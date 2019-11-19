package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.moving.DirectionIterator;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;

public class BackwardDiagonalRight implements IterableMove {

    private Comparator<PlayerMove> positionComparator = new AscendingPositionComparator();
    private DirectionIterator directionIterator = new DirectionIterator();

    @Override
    public SortedSet<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return directionIterator.iterate(moveSettings, this, fileFunction(), rankFunction());
    }

    @Override
    public IntBinaryOperator fileFunction() {
        return Integer::sum;
    }

    @Override
    public IntBinaryOperator rankFunction() {
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
