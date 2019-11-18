package chess.domain;

import chess.domain.util.Pair;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckRunner {

    public static final int PROTECTED_BY_HOW_MANY_PIECES = 4;
    private final ChessBoard chessBoard;
    private final PieceColor pieceColor;
    private final Pair<Position, Piece> king;
    private Pair<KingInCheck, Set<Position>> kingInCheckStrategy;

    public CheckRunner(ChessBoard chessBoard, PieceColor pieceColor) {
        this.chessBoard = chessBoard;
        this.pieceColor = pieceColor;
        this.king = chessBoard.q.getKing(pieceColor);
        this.kingInCheckStrategy = getStrategy(chessBoard, pieceColor);
    }

    private Pair<KingInCheck, Set<Position>> getStrategy(ChessBoard chessBoard, PieceColor pieceColor) {

        Pair<Position, Piece> king = chessBoard.q.getKing(pieceColor);
        //isKingInCheck if king is protected by own piece
        Position kingPosition = king.getLeft();
        Piece kingPiece = king.getRight();

        Collection<Position> adjacentPositions = chessBoard.q.getAdjacentPositions(kingPosition);
        Predicate<Position> nullPositionOrOppositeColor = position -> chessBoard.getModel().get(position) == null ||
                chessBoard.getModel().get(position).getPieceColor() == pieceColor.oppositeColor();
        Set<Position> openPositions = adjacentPositions.stream().filter(nullPositionOrOppositeColor).collect(Collectors.toSet());

        if (openPositions.size() >= PROTECTED_BY_HOW_MANY_PIECES) {

            // TODO CHANGE
            return Pair.of(new ProtectedKingInCheck(), openPositions);
        } else {
            return Pair.of(new ProtectedKingInCheck(), openPositions);
        }
    }

    public boolean isKingInCheck() {

        return this.kingInCheckStrategy.getLeft().isKingInCheck(chessBoard, pieceColor, kingInCheckStrategy.getRight());

    }

//    private Collection<Piece> findAllPiecesWithAttackDirections(PieceColor pieceColor, Collection<Move> attackDirections) {
//
//        attackDirections.forEach(moveDescriber -> {
//            Collection<MovingRule> allMovingRules = MovingRules.getAllMovingRules();
//            allMovingRules.forEach(movingRule -> {
//                Map<PieceColor, Collection<Move>> capturingMoves = movingRule.getCaptureParameters();
//                Collection<Move> moveDescribers = capturingMoves.get(pieceColor);
//                if (moveDescribers.contains(moveDescriber)) {
//                    PieceType pieceType = movingRule.getPieceType();
//                    System.out.println(pieceType);
//                }
//
//            });
//
//        });
//
//        return null;
//    }


}
