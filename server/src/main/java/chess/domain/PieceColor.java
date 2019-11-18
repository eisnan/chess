package chess.domain;

public enum PieceColor {

    WHITE, BLACK;

    public String getColorNotation() {
        return this.name().substring(0, 1);
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

    public boolean isPromotionRank(Rank rank) {
        if (this == WHITE) {
            return rank == Rank._8;
        } else {
            return rank == Rank._1;
        }
    }

}
