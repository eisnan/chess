package app.domain;

import app.domain.moving.MovingRule;
import app.domain.moving.MovingRules;

import java.util.List;

public class PositionResolver {


    public List<Position> getAvailablePositions(Piece piece, Position currentPosition) {


        // get checkMove algorithm
        MovingRule movingRule = MovingRules.getMovingRule(piece.getPieceType());

        // generate possible positions
        List<Position> possiblePositions = movingRule.getPossiblePositions(piece, currentPosition);


        return possiblePositions;
    }

}
