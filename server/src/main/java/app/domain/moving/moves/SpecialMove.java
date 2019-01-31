package app.domain.moving.moves;

import app.domain.PieceColor;
import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.comparators.DescendingPositionComparator;

import java.util.Comparator;

/**
 * Created by Gabs on 1/31/2019.
 */
public interface SpecialMove extends Move {

    Comparator<Position> getPositionComparator(PieceColor pieceColor);
}
