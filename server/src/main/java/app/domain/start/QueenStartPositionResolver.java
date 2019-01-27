package app.domain.start;

import app.domain.File;
import app.domain.Position;
import app.domain.Rank;
import app.domain.util.Tuple;

public class QueenStartPositionResolver {


    private File QUEEN_FILE = File.d;

    /**
     * Left tuple is white queen, right tuple is black queen
     */
    public Tuple<Position, Position> getQueensPosition() {
        return new Tuple<>(new Position(QUEEN_FILE, Rank._1), new Position(QUEEN_FILE, Rank._8));
    }
}
