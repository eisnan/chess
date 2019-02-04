package app.domain;

import app.domain.moving.PlayerMove;
import app.domain.moving.rules.MovingRule;
import app.domain.moving.rules.MovingRules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class PositionResolver {


    public Collection<PlayerMove> getAvailablePositions(ChessBoard chessBoard, Position currentPosition) {

        Piece piece = chessBoard.getModel().get(currentPosition);

        // get move algorithm
        MovingRule movingRule = MovingRules.getMovingRule(piece.getPieceType());



        // generate possible positions
        Collection<PlayerMove> movePositions = movingRule.getMovePositions(chessBoard, piece, currentPosition);
        Collection<PlayerMove> possiblePositions = new HashSet<>(movePositions);
        Collection<PlayerMove> attackingPositions = movingRule.getAttackingPositions(chessBoard, piece, currentPosition);
        possiblePositions.addAll(attackingPositions);



        return possiblePositions;
    }

    public void resolveFromPosition(ChessBoard chessBoard, Piece piece, Position toPosition) {

    }
}
