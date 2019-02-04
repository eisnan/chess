package app.domain.moving.moves;

import app.domain.PieceColor;
import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.comparators.DescendingPositionComparator;
import app.domain.moving.PlayerAction;
import app.domain.moving.PlayerMove;

import java.util.Comparator;

/**
 * Created by Gabs on 1/31/2019.
 */
public interface SpecialMove extends Move {

    Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor);
}
