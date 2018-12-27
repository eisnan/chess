package app.domain;

import app.domain.moving.MoveDescriber;
import app.domain.moving.rules.MovingRule;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckRunner {

    public boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor) {

        boolean isKingInCheck;

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);
        //isKingInCheck if king is protected by own piece
        Position kingPosition = king.getLeft();

        Collection<Position> adjacentPositions = chessBoard.getAdjacentPositions(kingPosition);
        Predicate<Position> nullPositionOrOppositeColor = position -> chessBoard.getModel().get(position) == null ||
                chessBoard.getModel().get(position).getPieceColor() == pieceColor.oppositeColor();
        Set<Position> vulnerable = adjacentPositions.stream().filter(nullPositionOrOppositeColor).collect(Collectors.toSet());


        System.out.println(vulnerable);

        // isKingInCheck if there are enemy knights on the board
        Collection<Tuple<Position, Piece>> knights = chessBoard.getPieces(PieceType.KNIGHT, pieceColor.oppositeColor());
        //no vulnerable and no knights
        if (vulnerable.isEmpty() && knights.isEmpty()) {
            return false;
        }

        // check if knights attack the king
        boolean knightsCheckKing = false;
        for (Tuple<Position, Piece> knight : knights) {
            Collection<Position> availablePositions = new PositionResolver().getAvailablePositions(chessBoard, knight.getRight(), knight.getLeft());
            Collection<Position> availablePositions2 = new PositionResolver().getAvailablePositions(chessBoard, knight.getRight(), knight.getLeft());
            knightsCheckKing |= availablePositions.contains(kingPosition) || availablePositions2.contains(kingPosition);
        }
        if (knightsCheckKing) {
            return true;
        }

        Collection<MoveDescriber> attackDirections = new PositionInterpreter().getAttackDirections(chessBoard, pieceColor, kingPosition, vulnerable);

        // look on these attack directions, see if any enemy piece is encountered and if it is, does it have this attack direction?

        findAllPiecesWithAttackDirections(pieceColor, attackDirections);

        return true;

    }

    private Collection<Piece> findAllPiecesWithAttackDirections(PieceColor pieceColor, Collection<MoveDescriber> attackDirections) {

        attackDirections.forEach(moveDescriber -> {
            Collection<MovingRule> allMovingRules = MovingRules.getAllMovingRules();
            allMovingRules.forEach(movingRule -> {
                Map<PieceColor, Collection<MoveDescriber>> capturingMoves = movingRule.getCapturingMoves();
                Collection<MoveDescriber> moveDescribers = capturingMoves.get(pieceColor);
                if (moveDescribers.contains(moveDescriber)) {
                    PieceType pieceType = movingRule.getPieceType();
                    System.out.println(pieceType);
                }

            });

        });

        return null;
    }


}
