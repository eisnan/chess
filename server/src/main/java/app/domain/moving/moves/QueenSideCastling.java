package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.comparators.DescendingPositionComparator;
import app.domain.moving.DirectionIterator;
import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gabs on 1/26/2019.
 */
public class QueenSideCastling implements Move, SpecialMove {

    private Comparator<PlayerMove> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<PlayerMove> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {

        DirectionIterator directionIterator = new DirectionIterator();
        LeftMove leftMove = new LeftMove();
        return directionIterator.iterate(moveSettings, leftMove, leftMove.fileFunction(), leftMove.rankFunction());


    }

    @Override
    public Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? whitePositionComparator : blackPositionComparator;
    }
}
