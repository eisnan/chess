package chess.domain.start;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.util.Pair;

public class QueenStartPositionResolver {


    private File QUEEN_FILE = File.d;

    /**
     * Left tuple is white queen, right tuple is black queen
     */
    public Pair<Position, Position> getQueensPosition() {
        return new Pair<>( Position.ofValid(QUEEN_FILE, Rank._1),  Position.ofValid(QUEEN_FILE, Rank._8));
    }
}
