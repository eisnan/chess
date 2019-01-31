package app.domain.moving.validators;

import app.domain.Position;
import app.domain.moving.moves.Move;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Gabs on 1/27/2019.
 */
public class GameRulesValidator {
    public void pawnCanMoveOnlyToOneDirection(Map<Move, Set<Position>> possiblePositions) {
        if (possiblePositions.keySet().size() > 1) {
            throw new AssertionError("Pawn can move only to one direction");
        }
    }
}
