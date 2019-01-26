package app.domain;

import app.domain.moving.rules.MovingRule;
import app.domain.moving.rules.MovingRules;

import java.util.ArrayList;
import java.util.Collection;

public class PositionResolver {


    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Position currentPosition) {

        Piece piece = chessBoard.getModel().get(currentPosition);

        // get move algorithm
        MovingRule movingRule = MovingRules.getMovingRule(piece.getPieceType());



        // generate possible positions
        Collection<Position> movePositions = movingRule.getMovePositions(chessBoard, piece, currentPosition);
        Collection<Position> possiblePositions = new ArrayList<>(movePositions);
        Collection<Position> attackingPositions = movingRule.getAttackingPositions(chessBoard, piece, currentPosition);
        possiblePositions.addAll(attackingPositions);



        return possiblePositions;
    }

    public void resolveFromPosition(ChessBoard chessBoard, Piece piece, Position toPosition) {

    }
}
