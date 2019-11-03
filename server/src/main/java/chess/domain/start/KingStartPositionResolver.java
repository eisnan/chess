package chess.domain.start;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.util.Pair;

public class KingStartPositionResolver {

    File KING_FILE = File.e;

    /**
     * Left tuple is white king, right tuple is black king
     */
    public Pair<Position, Position> getKingsPosition() {
        return new Pair<>(new Position(KING_FILE, Rank._1), new Position(KING_FILE, Rank._8));
    }
}
