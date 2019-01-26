package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.BiFunction;

/**
 * Created by Gabs on 1/26/2019.
 */
public class KingSideCastling implements MoveDescriber, SpecialMoveDescriber {
    @Override
    public Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return null;
    }

}
