package chess.domain.moving.moves;

import chess.domain.PieceColor;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;

/**
 * Special moves are validated at evaluation
 */
public interface SpecialMove extends Move {

    Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor);
}
