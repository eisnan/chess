package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.comparators.DescendingPositionComparator;
import app.domain.moving.MoveSettings;

import java.util.Comparator;
import java.util.SortedSet;

/**
 * Created by Gabs on 1/26/2019.
 */
public class QueenSideCastling implements Move, SpecialMove {

    private Comparator<Position> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<Position> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public SortedSet<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        return null;
    }

    @Override
    public Comparator<Position> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? whitePositionComparator : blackPositionComparator;
    }
}
