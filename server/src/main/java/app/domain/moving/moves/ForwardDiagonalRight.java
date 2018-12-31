package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.AscendingPositionComparator;
import app.domain.moving.DirectionIterator;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class ForwardDiagonalRight implements MoveDescriber {

    private Comparator<Position> positionComparator = new AscendingPositionComparator();


    @Override
    public Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        DirectionIterator directionIterator = new DirectionIterator();
        return directionIterator.iterate(moveSettings, this, fileFunction(), rankFunction());
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return (integer, integer2) -> integer + integer2;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return (integer, integer2) -> integer + integer2;
    }

    @Override
    public Comparator<Position> getPositionComparator() {
        return positionComparator;
    }


    @Override
    public boolean equals(Object obj) {
        return this.getClass().equals(obj.getClass());
    }
}
