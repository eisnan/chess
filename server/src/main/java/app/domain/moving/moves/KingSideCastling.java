package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.MoveSettings;
import app.domain.moving.SpecialMove;

import java.util.Collection;
import java.util.SortedSet;

/**
 * Created by Gabs on 1/26/2019.
 */
public class KingSideCastling implements Move, SpecialMove {
    @Override
    public SortedSet<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return null;
    }

}
