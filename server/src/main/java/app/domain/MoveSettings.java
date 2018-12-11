package app.domain;

public class MoveSettings {

    private Position currentPosition;
    private PieceColor pieceColor;
    private int spaces;

    public MoveSettings(Position currentPosition, PieceColor pieceColor, int spaces) {
        this.currentPosition = currentPosition;
        this.pieceColor = pieceColor;
        this.spaces = spaces;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public int getSpaces() {
        return spaces;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }
}
