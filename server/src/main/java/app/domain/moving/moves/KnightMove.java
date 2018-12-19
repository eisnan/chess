package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;

import java.util.Collection;
import java.util.function.BiFunction;

public class KnightMove implements MoveDescriber {
    @Override
    public Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return null;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return null;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return null;
    }
}
