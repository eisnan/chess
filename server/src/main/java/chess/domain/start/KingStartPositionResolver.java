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
        return new Pair<>(Position.ofValid(KING_FILE, Rank._1), Position.ofValid(KING_FILE, Rank._8));
    }
}
