package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;

/**
 * Created by Gabs on 1/31/2019.
 */
public interface IterableMove extends Move {

    @Override
    SortedSet<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);

    IntBinaryOperator fileFunction();

    IntBinaryOperator rankFunction();

    Comparator<PlayerMove> getPositionComparator();
}
