package app.domain.moving;

import app.domain.InvalidPositionException;
import app.domain.Position;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@Slf4j
public class MoveIterator {


    public List<Position> iterate(MoveSettings moveSettings, MoveType moveType, BiFunction<Integer, Integer, Integer> fileFunction, BiFunction<Integer, Integer, Integer> rankFunction) {
        List<Position> possiblePositions = new ArrayList<>();
        Position currentPosition = moveSettings.getCurrentPosition();
        Integer limit = moveSettings.getMaxLimit().get(moveType);
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
