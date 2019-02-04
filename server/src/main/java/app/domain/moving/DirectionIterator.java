package app.domain.moving;

import app.domain.InvalidPositionException;
import app.domain.Position;
import app.domain.moving.moves.IterableMove;
import lombok.extern.slf4j.Slf4j;

import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@Slf4j
public class DirectionIterator {


    public TreeSet<PlayerMove> iterate(MoveSettings moveSettings, IterableMove move, BiFunction<Integer, Integer, Integer> fileFunction, BiFunction<Integer, Integer, Integer> rankFunction) {
        TreeSet<PlayerMove> playerMoves = new TreeSet<>(move.getPositionComparator());
        Position currentPosition = moveSettings.getCurrentPosition();
        Integer limit = moveSettings.getSettings().get(move);
        Stream.iterate(1, x -> x + 1)
                .limit(limit)
                .forEach(integer -> {
                    try {
                        Position position = new Position(fileFunction.apply(currentPosition.getFile().ordinal(), integer),
                                rankFunction.apply(currentPosition.getRank().ordinal(), integer));
                        playerMoves.add(new PlayerMove(moveSettings.getPiece(), currentPosition, position));
                    } catch (InvalidPositionException ex) {
                        log.info("integer:" + integer);
                        log.info(ex.toString());
                    }
                });
        return playerMoves;
    }
}
