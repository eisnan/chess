package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveIterator;
import app.domain.moving.MoveSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class BackwardDiagonalLeft implements MoveDescriber {
    @Override
    public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        MoveIterator moveIterator = new MoveIterator();
        List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, fileFunction() , rankFunction());
        return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return (integer, integer2) -> integer - integer2;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return (integer, integer2) -> integer - integer2;
    }
}
