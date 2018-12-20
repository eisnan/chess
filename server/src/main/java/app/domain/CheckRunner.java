package app.domain;

import app.domain.util.Tuple;

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
        }

        return true;

    }


}
