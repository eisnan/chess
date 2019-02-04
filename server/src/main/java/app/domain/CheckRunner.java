package app.domain;

import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerMove;
import app.domain.moving.moves.IterableMove;
import app.domain.moving.moves.Move;
import app.domain.moving.moves.SpecialMove;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckRunner {

    private final ChessBoard chessBoard;
    private final PieceColor pieceColor;
    private final Tuple<Position, Piece> king;
    private Tuple<KingInCheck, Set<Position>> kingInCheckStrategy;

    public CheckRunner(ChessBoard chessBoard, PieceColor pieceColor) {
        this.chessBoard = chessBoard;
        this.pieceColor = pieceColor;
        this.king = chessBoard.getKing(pieceColor);
        this.kingInCheckStrategy = getStrategy(chessBoard, pieceColor);
    }

    private Tuple<KingInCheck, Set<Position>> getStrategy(ChessBoard chessBoard, PieceColor pieceColor) {

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);
        //isKingInCheck if king is protected by own piece
        Position kingPosition = king.getLeft();
        Piece kingPiece = king.getRight();

        Collection<Position> adjacentPositions = chessBoard.getAdjacentPositions(kingPosition);
        Predicate<Position> nullPositionOrOppositeColor = position -> chessBoard.getModel().get(position) == null ||
                chessBoard.getModel().get(position).getPieceColor() == pieceColor.oppositeColor();
        Set<Position> openPositions = adjacentPositions.stream().filter(nullPositionOrOppositeColor).collect(Collectors.toSet());

        if (openPositions.size() >= 4) {
            return Tuple.of(new UnprotectedKingInCheck(), openPositions);
        } else {
            return Tuple.of(new ProtectedKingInCheck(), openPositions);
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


    public boolean isKingInCheck2(ChessBoard chessBoard, PieceColor pieceColor) {

        boolean isKingInCheck = false;

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);


        return isKingInCheck;

    }

}
