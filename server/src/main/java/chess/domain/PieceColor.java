package chess.domain;

public enum PieceColor {

    WHITE, BLACK;

    public String getColorNotation() {
        return this.name().substring(0, 1).toLowerCase();
    }

    public static PieceColor getByColorNotation(String symbol) {
        for (PieceColor pieceColor : PieceColor.values()) {
            if (symbol.equals(pieceColor.getColorNotation())) {
                return pieceColor;
            }
        }
        throw new IllegalArgumentException("Incorrect symbol for color");
    }


    public boolean isOppositeColor(PieceColor pieceColor) {
        return this != pieceColor;
    }

    public PieceColor oppositeColor() {
        return this == WHITE ? BLACK : WHITE;
    }

    public boolean isFirstRank(Rank rank) {
        if (this == WHITE) {
            return rank == Rank._2;
        } else {
            return rank == Rank._7;
        }
    }
}
