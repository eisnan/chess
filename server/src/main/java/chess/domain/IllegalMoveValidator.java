package chess.domain;

import chess.domain.moving.PlayerMove;

import java.util.Collections;
import java.util.Set;

public class IllegalMoveValidator {

    public Set<PlayerMove> checkIllegalMoves() {
        // king cannot be captured

        return Collections.emptySet();
    }
}
