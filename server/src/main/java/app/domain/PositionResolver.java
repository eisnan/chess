package app.domain;

import app.domain.moving.MovingRule;
import app.domain.moving.MovingRules;

import java.util.Collection;
import java.util.List;

public class PositionResolver {


    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {


        // get checkMove algorithm
        MovingRule movingRule = MovingRules.getMovingRule(piece.getPieceType());

        // generate possible positions
        Collection<Position> possiblePositions = movingRule.getPossiblePositions(chessBoard, piece, currentPosition);


        return possiblePositions;
    }

}
