package app.domain;

public enum PieceColor {

    WHITE, BLACK;

    public String getColorNotation() {
        return this.name().substring(0, 1).toLowerCase();
    }

    public boolean isOppositeColor(PieceColor pieceColor) {
        return this != pieceColor;
    }

    public PieceColor oppositeColor() {
        return this == WHITE ? BLACK : WHITE;
    }
}
