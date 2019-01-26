package app.domain.moving;

import app.domain.Position;

import java.util.Comparator;
import java.util.function.BiFunction;

/**
 * Marker interface for non-iterable moves
 */
public interface SpecialMoveDescriber extends MoveDescriber {

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
