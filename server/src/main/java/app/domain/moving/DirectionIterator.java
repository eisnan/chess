package app.domain.moving;

import app.domain.InvalidPositionException;
import app.domain.Position;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@Slf4j
public class DirectionIterator {


    public Collection<Position> iterate(MoveSettings moveSettings, MoveDescriber moveDescriber, BiFunction<Integer, Integer, Integer> fileFunction, BiFunction<Integer, Integer, Integer> rankFunction) {
        Collection<Position> possiblePositions = new TreeSet<>(moveDescriber.getPositionComparator());
        Position currentPosition = moveSettings.getCurrentPosition();
        Integer limit = moveSettings.getMovingSettings().get(moveDescriber);
        Stream.iterate(1, x -> x + 1)
                .limit(limit)
                .forEach(integer -> {
                    try {
                        Position position = new Position(fileFunction.apply(currentPosition.getFile().ordinal(), integer),
                                rankFunction.apply(currentPosition.getRank().ordinal(), integer));
                        possiblePositions.add(position);
                    } catch (InvalidPositionException ex) {
                        log.info("integer:" + integer);
                        log.info(ex.toString());
                    }
                });
        return possiblePositions;
    }
}
