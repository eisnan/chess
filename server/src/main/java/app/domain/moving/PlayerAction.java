package app.domain.moving;

import app.domain.*;
import app.domain.moving.rules.PawnMovingRule;

public class PlayerAction {

    private PawnPromoter pawnPromoter = new PawnPromoter();

    public MoveType move(ChessBoard chessBoard, Piece piece, Position fromPosition, Position toPosition) {

        // isKingInCheck for uncapturable pieces



        // isKingInCheck if king is left in isKingInCheck





        chessBoard.getModel().put(fromPosition, null);
        chessBoard.getModel().put(toPosition, piece);

        if (piece.getPieceType() == PieceType.PAWN) {
            boolean enPassant = new PawnMovingRule().isEnPassant(chessBoard, piece, fromPosition, toPosition);

            if (enPassant) {
                Position epCapture;
                if (piece.getPieceColor() == PieceColor.WHITE) {
                    epCapture = new Position(toPosition.getFile(), toPosition.getRank().previous());
                } else {
                    epCapture = new Position(toPosition.getFile(), toPosition.getRank().next());
                }
                chessBoard.getModel().put(epCapture, null);
            }

            switch (piece.getPieceColor()) {

                case WHITE:
                    if (toPosition.getRank() == Rank._8) {
                        pawnPromoter.promote(chessBoard, piece);
                    }
                    break;
                case BLACK:
                    if (toPosition.getRank() == Rank._1) {
                        pawnPromoter.promote(chessBoard, piece);
                    }
                    break;
            }
        }
        chessBoard.addMove(new Move2(piece, fromPosition, toPosition));
        return null;
    }
}
