package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.DirectionIterator;
import app.domain.moving.MoveSettings;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiFunction;

public class ForwardMove implements MoveDescriber {
    @Override
    public Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        DirectionIterator directionIterator = new DirectionIterator();
        return directionIterator.iterate(moveSettings, this, fileFunction(), rankFunction());
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return (integer, integer2) -> integer;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return (integer, integer2) -> integer + integer2;
    }
}
