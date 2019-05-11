package chess.domain.moving.moves;

import chess.domain.PieceColor;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;

/**
 * Created by Gabs on 1/31/2019.
 */
public interface SpecialMove extends Move {

    Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor);
}
