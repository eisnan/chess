package chess.domain;

import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRule;
import chess.domain.moving.rules.MovingRules;

import java.util.*;
import java.util.stream.Collectors;

public class PositionResolver {

    private MoveTypeResolver moveTypeResolver = new MoveTypeResolver();
    private IllegalMoveValidator illegalMoveValidator = new IllegalMoveValidator();
    CheckEvaluator checkEvaluator = new CheckEvaluator();

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
        Collection<PlayerMove> possibleMoves = new HashSet<>();
        possibleMoves.addAll(movePositions);
        Collection<PlayerMove> attackingPositions = movingRule.getAttackingPositions(chessBoard, piece, currentPosition);

        possibleMoves.addAll(attackingPositions);

        return possibleMoves;
    }

}
