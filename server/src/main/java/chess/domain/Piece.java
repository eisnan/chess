package chess.domain;

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
        return pieceColor.getColorNotation() + pieceType.getTypeNotation();
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

    public String getUnicode() {
        switch (pieceColor) {
            case WHITE:
                switch (pieceType) {
                    case PAWN:
                        return "&#9817;";
                    case KING:
                        return "&#9812;";
                    case QUEEN:
                        return "&#9813;";
                    case BISHOP:
                        return "&#9815;";
                    case KNIGHT:
                        return "&#9816;";
                    case ROOK:
                        return "&#9814;";
                }
                break;
            case BLACK:
                switch (pieceType) {
                    case PAWN:
                        return "&#9823;";
                    case KING:
                        return "&#9818;";
                    case QUEEN:
                        return "&#9819;";
                    case BISHOP:
                        return "&#9821;";
                    case KNIGHT:
                        return "&#9822;";
                    case ROOK:
                        return "&#9820;";
                }
                break;
        }
        return "";
    }
}
