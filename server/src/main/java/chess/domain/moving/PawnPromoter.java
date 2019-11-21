package chess.domain.moving;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.Position;

public class PawnPromoter {
    public void promote(ChessBoard chessBoard, Position position, Piece promotedPiece) {
        chessBoard.getModel().put(position, promotedPiece);
    }
}
