package app.domain.comparators;

import app.domain.Position;
import app.domain.moving.PlayerMove;

import java.util.Comparator;

public class AscendingPositionComparator implements Comparator<PlayerMove> {
    @Override
    public int compare(PlayerMove o1, PlayerMove o2) {
        if (o1.getToPosition().getFile().ordinal() == o2.getToPosition().getFile().ordinal()) {
            return Integer.compare(o1.getToPosition().getRank().ordinal(), o2.getToPosition().getRank().ordinal());
        } else {
            return Integer.compare(o1.getToPosition().getFile().ordinal(), o2.getToPosition().getFile().ordinal());
        }
    }
}
