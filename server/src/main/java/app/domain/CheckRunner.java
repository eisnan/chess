package app.domain;

import app.domain.util.Tuple;
import javafx.geometry.Pos;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckRunner {

    public boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor) {

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
        if (knights.isEmpty()) {
            return false;
        } else {
            // check if knights attack the king
            for (Tuple<Position, Piece> knigth: knights) {
                Collection<Position> availablePositions = new PositionResolver().getAvailablePositions(chessBoard, knigth.getRight(), knigth.getLeft());
                Collection<Position> availablePositions2 = new PositionResolver().getAvailablePositions(chessBoard, knigth.getRight(), knigth.getLeft());
                return availablePositions.contains(kingPosition) || availablePositions2.contains(kingPosition);


            }
        }

        return true;

    }


}
