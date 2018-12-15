package app.domain.moving;

import app.domain.*;

public class Mover {


    public void move(ChessBoard chessBoard, Piece piece, Position fromPosition, Position toPosition) {

        chessBoard.getModel().put(fromPosition, null);
        chessBoard.getModel().put(toPosition, piece);

        if (piece.getPieceType() == PieceType.PAWN) {
            boolean enPassant = new PawnMovingRule().isEnPassant(piece, fromPosition, toPosition);

            if (enPassant) {
                Position epCapture;
                if (piece.getPieceColor() == PieceColor.WHITE) {
                    epCapture = new Position(toPosition.getFile(), toPosition.getRank().previous());
                } else {
                    epCapture = new Position(toPosition.getFile(), toPosition.getRank().next());
                }
                chessBoard.getModel().put(epCapture, null);
            }
        }
        chessBoard.addMove(new Move2(piece, fromPosition, toPosition));
    }
}
