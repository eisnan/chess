package app.domain;

import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.SpecialMoveDescriber;
import app.domain.moving.rules.KingMovingRule;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckRunner {

    public boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor) {

        boolean isKingInCheck = false;

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);
        //isKingInCheck if king is protected by own piece
        Position kingPosition = king.getLeft();
        Piece kingPiece = king.getRight();

        Collection<Position> adjacentPositions = chessBoard.getAdjacentPositions(kingPosition);
        Predicate<Position> nullPositionOrOppositeColor = position -> chessBoard.getModel().get(position) == null ||
                chessBoard.getModel().get(position).getPieceColor() == pieceColor.oppositeColor();
        Set<Position> openPositions = adjacentPositions.stream().filter(nullPositionOrOppositeColor).collect(Collectors.toSet());


        System.out.println(openPositions);


        // isKingInCheck if there are enemy knights on the board
        Collection<Tuple<Position, Piece>> knights = chessBoard.getPieces(PieceType.KNIGHT, pieceColor.oppositeColor());
        //no vulnerable and no knights
        if (openPositions.isEmpty() && knights.isEmpty()) {
            return false;
        }

        // check if knights attack the king
        boolean knightsCheckKing = false;
        for (Tuple<Position, Piece> knight : knights) {
            Collection<Position> availablePositions = new PositionResolver().getAvailablePositions(chessBoard, knight.getLeft());
            Collection<Position> availablePositions2 = new PositionResolver().getAvailablePositions(chessBoard, knight.getLeft());
            knightsCheckKing |= availablePositions.contains(kingPosition) || availablePositions2.contains(kingPosition);
        }
        if (knightsCheckKing) {
            return true;
        }


        // based on open positions determine open (vulnerable) vectors/directions
        Collection<MoveDescriber> openDirections = new PositionInterpreter().getAttackDirections(chessBoard, pieceColor, kingPosition, openPositions);

        System.out.println(openDirections);


        // follow those directions, see if encounter enemy piece
        Map<MoveDescriber, Integer> movingSettings = MovingRules.getMovingRule(kingPiece.getPieceType()).getMoveDescribers().stream()
                .filter(moveDescriber -> !(moveDescriber instanceof SpecialMoveDescriber))
                .filter(openDirections::contains).collect(Collectors.toMap(moveDescriber -> moveDescriber, movingPositions -> 8));

        for (MoveDescriber moveDescriber : openDirections) {
            Collection<Position> positions = moveDescriber.checkMove(chessBoard, new MoveSettings(kingPosition, kingPiece, new KingMovingRule(), movingSettings));
            System.out.println(positions);
            Optional<Tuple<Position, Piece>> firstPieceOnDirection = new PositionInterpreter().findFirstPieceOnDirection(chessBoard, moveDescriber, positions);
            if (firstPieceOnDirection.isPresent() && kingPiece.getPieceColor().isOppositeColor(firstPieceOnDirection.get().getRight().getPieceColor())) {
                Collection<Position> attackingPositions = MovingRules.getMovingRule(firstPieceOnDirection.get().getRight().getPieceType()).getAttackingPositions(chessBoard, firstPieceOnDirection.get().getRight(), firstPieceOnDirection.get().getLeft());
                System.out.println(attackingPositions);
                if (attackingPositions.contains(kingPosition)) {
                    return true;
                }
            }
        }

        // look on these attack directions, see if any enemy piece is encountered and if it is, does it have this attack direction?

//        findAllPiecesWithAttackDirections(pieceColor, openDirections);

        return false;

    }

//    private Collection<Piece> findAllPiecesWithAttackDirections(PieceColor pieceColor, Collection<MoveDescriber> attackDirections) {
//
//        attackDirections.forEach(moveDescriber -> {
//            Collection<MovingRule> allMovingRules = MovingRules.getAllMovingRules();
//            allMovingRules.forEach(movingRule -> {
//                Map<PieceColor, Collection<MoveDescriber>> capturingMoves = movingRule.getCaptureParameters();
//                Collection<MoveDescriber> moveDescribers = capturingMoves.get(pieceColor);
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
