package app.domain.moving;

import app.domain.*;
import app.domain.moving.validators.PawnValidator;

public class PlayerAction {

    private PawnPromoter pawnPromoter = new PawnPromoter();

    public MoveType move(ChessBoard chessBoard, Piece piece, Position fromPosition, Position toPosition) {

        // isKingInCheck for uncapturable pieces


        // isKingInCheck if king is left in isKingInCheck


        chessBoard.getModel().put(fromPosition, null);
        chessBoard.getModel().put(toPosition, piece);

        if (piece.getPieceType() == PieceType.PAWN) {
            boolean enPassant = PawnValidator.isEnPassant(chessBoard, piece, fromPosition, toPosition);

            if (enPassant) {
                Position epCapture;
                if (piece.isWhitePiece()) {
                    epCapture = new Position(toPosition.getFile(), toPosition.getRank().previous());
                } else {
                    epCapture = new Position(toPosition.getFile(), toPosition.getRank().next());
                }
                chessBoard.getModel().put(epCapture, null);
            }

            if (piece.isWhitePiece() && toPosition.getRank() == Rank._8) {
                pawnPromoter.promote(chessBoard, piece);
            }
            if (piece.isBlackPiece() && toPosition.getRank() == Rank._1) {
                pawnPromoter.promote(chessBoard, piece);
            }
        }
        chessBoard.addMove(new PlayerMove(piece, fromPosition, toPosition));

        return null;
    }

    public MoveType move(ChessBoard chessBoard, PlayerMove playerMove) {
        move(chessBoard, playerMove.getPiece(), playerMove.getFromPosition(), playerMove.getToPosition());

        return null;
    }
}
