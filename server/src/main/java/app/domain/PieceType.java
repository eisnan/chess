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

    public static PieceType getByNotationSymbol(String notationSymbol) {
        for (PieceType pieceType : PieceType.values()) {
            if (notationSymbol.equals(pieceType.getNotationSymbol())) {
                return pieceType;
            }
        }
        throw new IllegalArgumentException();
    }
}
