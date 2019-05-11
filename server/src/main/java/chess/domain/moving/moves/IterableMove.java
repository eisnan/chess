package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.function.BiFunction;

/**
 * Created by Gabs on 1/31/2019.
 */
public interface IterableMove extends Move {

    @Override
    SortedSet<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);

    BiFunction<Integer, Integer, Integer> fileFunction();

    BiFunction<Integer, Integer, Integer> rankFunction();

    Comparator<PlayerMove> getPositionComparator();
}
