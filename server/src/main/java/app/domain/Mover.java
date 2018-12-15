package app.domain;

public class Mover {
    public void move(ChessBoard chessBoard, Piece piece, Position fromPosition, Position toPosition) {

        chessBoard.getModel().put(fromPosition, null);
        chessBoard.getModel().put(toPosition, piece);
        chessBoard.addMove(new Move2(piece, fromPosition, toPosition));
    }
}
