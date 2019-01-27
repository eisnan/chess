package app.domain.moving;

import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.moving.moves.Move;

import java.util.Comparator;
import java.util.function.BiFunction;

/**
 * Marker interface for non-iterable moves
 */
public interface SpecialMove extends Move {

    @Override
    default BiFunction<Integer, Integer, Integer> fileFunction() {
        throw new UnsupportedOperationException();
    }

    @Override
    default BiFunction<Integer, Integer, Integer> rankFunction() {
        throw new UnsupportedOperationException();
    }

    @Override
    default Comparator<Position> getPositionComparator() {
        return new AscendingPositionComparator();
    }
}
