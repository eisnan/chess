package app.domain;

import app.domain.moving.rules.MovingRule;
import app.domain.moving.rules.MovingRules;

import java.util.Collection;

public class PositionResolver {


    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {


        // get checkMove algorithm
        MovingRule movingRule = MovingRules.getMovingRule(piece.getPieceType());

        

        // generate possible positions
        Collection<Position> possiblePositions = movingRule.getPossiblePositions(chessBoard, piece, currentPosition);


        return possiblePositions;
    }

}
