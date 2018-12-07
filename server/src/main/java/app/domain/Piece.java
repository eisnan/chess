package app.domain;

import java.util.Objects;

public class Piece {

    private PiecesColor piecesColor;
    private PieceType pieceType;

    public Piece(PiecesColor piecesColor, PieceType pieceType) {
        this.piecesColor = piecesColor;
        this.pieceType = pieceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return piecesColor == piece.piecesColor &&
                pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piecesColor, pieceType);
    }

    @Override
    public String toString() {
        return "Piece{" + "piecesColor=" + piecesColor + ", pieceType=" + pieceType + '}';
    }
}
