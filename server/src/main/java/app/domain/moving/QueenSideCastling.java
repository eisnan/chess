package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;
import app.domain.moving.moves.Move;

import java.util.Collection;
import java.util.SortedSet;

/**
 * Created by Gabs on 1/26/2019.
 */
public class QueenSideCastling implements Move, SpecialMove {
    @Override
    public SortedSet<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return null;
    }

}
