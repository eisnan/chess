package chess.domain.moving.validators;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;

import java.util.Map;
import java.util.Set;

/**
 * Created by Gabs on 1/27/2019.
 */
public class GameRulesValidator {
    public void pawnCanMoveOnlyToOneDirection(Map<Move, Set<PlayerMove>> possiblePositions) {
        if (possiblePositions.keySet().size() > 1) {
            throw new AssertionError("Pawn can move only to one direction");
        }
    }
}
