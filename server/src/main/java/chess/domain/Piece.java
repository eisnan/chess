package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The class is self cached based on enum values, meaning it can only have a limited amount of values
 */
public class Piece {

    private PieceColor pieceColor;
    private PieceType pieceType;

    private Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }

    public static Piece of(PieceColor pieceColor, PieceType pieceType) {
        return PieceCache.getPieceCache().get(pieceColor).get(pieceType);
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

    private static class PieceCache {
        private static Map<PieceColor, Map<PieceType,Piece>> pieceCache = new HashMap<>();

        static {
            for (PieceColor color : PieceColor.values()) {
                Map<PieceType,Piece> pieceTypes  = new HashMap<>();
                for (PieceType type : PieceType.values()) {
                    Piece piece = new Piece(color, type);
                    pieceTypes.put(type, piece);

                }
                pieceCache.put(color, pieceTypes);
            }
        }

        public static Map<PieceColor, Map<PieceType,Piece>> getPieceCache() {
            return pieceCache;
        }

    }
}
