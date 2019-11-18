package chess.domain.moving;

import chess.domain.InvalidPositionException;
import chess.domain.Position;
import chess.domain.moving.moves.IterableMove;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
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
                        Optional<Position> position = Position.of(fileFunction.apply(currentPosition.getFile().ordinal(), integer),
                                rankFunction.apply(currentPosition.getRank().ordinal(), integer));
                        position.ifPresent(pos -> playerMoves.add(new PlayerMove(moveSettings.getPiece(), currentPosition, pos, MoveType.MOVE)));
                });
        return playerMoves;
    }
}
