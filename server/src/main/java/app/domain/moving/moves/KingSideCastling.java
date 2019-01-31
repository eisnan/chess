package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.comparators.DescendingPositionComparator;
import app.domain.moving.DirectionIterator;
import app.domain.moving.MoveSettings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gabs on 1/26/2019.
 */
public class KingSideCastling implements SpecialMove {

    private Comparator<Position> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<Position> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public Set<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        DirectionIterator directionIterator = new DirectionIterator();
        RightMove rightMove = new RightMove();
        return directionIterator.iterate(moveSettings, rightMove, rightMove.fileFunction(), rightMove.rankFunction());
    }

    @Override
    public Comparator<Position> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? blackPositionComparator : whitePositionComparator;
    }
}
