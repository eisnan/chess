package app.domain;

public class Mover {
    public void move(Piece piece, Position position) {

        ChessBoard.INSTANCE.getModel().put(position, piece);
    }
}
