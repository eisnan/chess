package chess.domain;

import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRule;
import chess.domain.moving.rules.MovingRules;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class PositionResolver {

    private MoveTypeResolver moveTypeResolver = new MoveTypeResolver();

    public Collection<PlayerMove> getValidMoves(ChessBoard chessBoard, Position currentPosition) {

        Piece piece = chessBoard.getModel().get(currentPosition);

        Comparator<PlayerMove> comparator;
        if (piece.getPieceColor() == PieceColor.WHITE) {
            comparator = new DescendingPositionComparator();
        } else {
            comparator = new AscendingPositionComparator();
        }

        // get move algorithm
        MovingRule movingRule = MovingRules.getMovingRule(piece.getPieceType());


        // generate possible positions
        Collection<PlayerMove> movePositions = movingRule.getMovePositions(chessBoard, piece, currentPosition);
        Collection<PlayerMove> possiblePositions = new TreeSet<>(comparator);
        possiblePositions.addAll(movePositions);
        Collection<PlayerMove> attackingPositions = movingRule.getAttackingPositions(chessBoard, piece, currentPosition);
        possiblePositions.addAll(attackingPositions);

        return moveTypeResolver.update(chessBoard, possiblePositions);

    }

}
