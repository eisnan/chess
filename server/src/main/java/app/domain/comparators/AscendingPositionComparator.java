package app.domain.comparators;

import app.domain.Position;

import java.util.Comparator;

public class AscendingPositionComparator implements Comparator<Position> {
    @Override
    public int compare(Position o1, Position o2) {
        if (o1.getFile().ordinal() == o2.getFile().ordinal()) {
            return Integer.compare(o1.getRank().ordinal(), o2.getRank().ordinal());
        } else {
            return Integer.compare(o1.getFile().ordinal(), o2.getFile().ordinal());
        }
    }
}
