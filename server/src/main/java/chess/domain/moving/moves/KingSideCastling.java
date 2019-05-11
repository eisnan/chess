package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.PieceColor;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.DirectionIterator;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.Set;

/**
 * Created by Gabs on 1/26/2019.
 */
public class KingSideCastling implements SpecialMove {

    private Comparator<PlayerMove> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<PlayerMove> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        DirectionIterator directionIterator = new DirectionIterator();
        RightMove rightMove = new RightMove();
        return directionIterator.iterate(moveSettings, rightMove, rightMove.fileFunction(), rightMove.rankFunction());
    }

    @Override
    public Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? blackPositionComparator : whitePositionComparator;
    }
}
