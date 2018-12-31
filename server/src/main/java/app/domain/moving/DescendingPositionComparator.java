package app.domain.moving;

import app.domain.Position;

import java.util.Comparator;

public class DescendingPositionComparator implements Comparator<Position> {
    @Override
    public int compare(Position o2, Position o1) {
        if (o2.getFile().ordinal() == o1.getFile().ordinal()) {
            return Integer.compare(o2.getRank().ordinal(), o1.getRank().ordinal());
        } else {
            return Integer.compare(o2.getFile().ordinal(), o1.getFile().ordinal());
        }
    }
}
