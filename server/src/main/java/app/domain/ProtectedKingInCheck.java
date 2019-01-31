package app.domain;

/**
 * Created by Gabs on 1/27/2019.
 */
public class ProtectedKingInCheck implements KingInCheck{
    @Override
    public boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor) {
        return false;
    }
}
