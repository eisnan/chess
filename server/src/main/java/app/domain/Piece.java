package app.domain;

import java.util.List;
import java.util.Objects;

public class Piece  {

    private PieceColor pieceColor;
    private PieceType pieceType;

    public Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }


    public static Piece getWhitePiece(PieceType pieceType) {
        return new Piece(PieceColor.WHITE, pieceType);
    }

    public static Piece getBlackPiece(PieceType pieceType) {
        return new Piece(PieceColor.BLACK, pieceType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceColor == piece.pieceColor &&
                pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType);
    }

    @Override
    public String toString() {
        return "Piece{" + "pieceColor=" + pieceColor + ", pieceType=" + pieceType + '}';
    }

    public String getNotation() {
        return pieceColor.getColorNotation() + pieceType.getNotationSymbol();
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }



}
