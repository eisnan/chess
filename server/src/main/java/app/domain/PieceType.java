package app.domain;

public enum PieceType {
    PAWN("P"), KING("K"), QUEEN("Q"), BISHOP("B"), KNIGHT("N"), ROOK("R");

    private String notationSymbol;

    PieceType(String notationSymbol) {
        this.notationSymbol = notationSymbol;
    }

    public String getNotationSymbol() {
        return notationSymbol;
    }
}
