package chess.domain;

public enum PieceType {
    PAWN(""), KING("K"), QUEEN("Q"), BISHOP("B"), KNIGHT("N"), ROOK("R");

    private String notationSymbol;

    PieceType(String notationSymbol) {
        this.notationSymbol = notationSymbol;
    }

    public String getTypeNotation() {
        return notationSymbol;
    }

    public static PieceType getByNotationSymbol(String notationSymbol) {
        for (PieceType pieceType : PieceType.values()) {
            if (notationSymbol.equals(pieceType.getTypeNotation())) {
                return pieceType;
            }
        }
        //for pawns should be rank
        for (File f : File.values()) {
            if (notationSymbol.equals(f.name())) {
                return PAWN;
            }
        }
        throw new IllegalArgumentException();
    }

}
