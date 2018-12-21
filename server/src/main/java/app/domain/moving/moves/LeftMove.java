package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.DirectionIterator;
import app.domain.moving.MoveSettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class LeftMove implements MoveDescriber {

    private final DirectionIterator directionIterator;

    public LeftMove() {
        directionIterator = new DirectionIterator();
    }

    @Override
    public Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        List<Position> possiblePositions = directionIterator.iterate(moveSettings, this, fileFunction(), rankFunction());
        return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return (integer, integer2) -> integer - integer2;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return (integer, integer2) -> integer;
    }
}
