package chess.domain.moving;

import chess.domain.Position;
import chess.domain.moving.moves.IterableMove;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

@Slf4j
public class DirectionIterator {


    public SortedSet<PlayerMove> iterate(MoveSettings moveSettings, IterableMove move, IntBinaryOperator fileFunction, IntBinaryOperator rankFunction) {
        TreeSet<PlayerMove> playerMoves = new TreeSet<>(move.getPositionComparator());
        Position currentPosition = moveSettings.getCurrentPosition();
        Integer limit = moveSettings.getSettings().get(move);
        Stream.iterate(1, x -> x + 1)
                .limit(limit)
                .forEach(integer -> {
                        Optional<Position> position = Position.of(fileFunction.applyAsInt(currentPosition.getFile().ordinal(), integer),
                                rankFunction.applyAsInt(currentPosition.getRank().ordinal(), integer));
                        position.ifPresent(pos -> playerMoves.add(PlayerMove.of(moveSettings.getPiece(), currentPosition, pos, MoveType.MOVE)));
                });
        return playerMoves;
    }
}
