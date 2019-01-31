package app.domain.start;

import app.domain.File;
import app.domain.Position;
import app.domain.Rank;
import app.domain.util.Tuple;

public class KingStartPositionResolver {

    File KING_FILE = File.e;

    /**
     * Left tuple is white king, right tuple is black king
     */
    public Tuple<Position, Position> getKingsPosition() {
        return new Tuple<>(new Position(KING_FILE, Rank._1), new Position(KING_FILE, Rank._8));
    }
}