package app.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Piece {

    private PieceColor pieceColor;
    private PieceType pieceType;
    private Collection<Position> attackingPositions = Collections.emptySet();

    public Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public Piece(String notation) {
        this.pieceColor = PieceColor.getByColorNotation(notation.substring(0, 1));
        this.pieceType = PieceType.getByNotationSymbol(notation.substring(1, 2));
    }

    public static Piece getWhitePiece(PieceType pieceType) {
        return new Piece(PieceColor.WHITE, pieceType);
    }

    public static Piece getBlackPiece(PieceType pieceType) {
        return new Piece(PieceColor.BLACK, pieceType);
    }

    public Collection<Position> getAttackingPositions() {
        return Collections.unmodifiableCollection(attackingPositions);
    }

    public void setAttackingPositions(Collection<Position> attackingPositions) {
        this.attackingPositions = attackingPositions;
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

    public boolean canBeCaptured() {
        return pieceType != PieceType.KING;
    }

    public boolean isWhitePiece() {
        return pieceColor == PieceColor.WHITE;
    }

    public boolean isBlackPiece() {
        return pieceColor == PieceColor.BLACK;
    }

}
