package app.domain;

public class MovingRules {
    public static MovingRule getMovingRule(PieceType pieceType) {
        switch (pieceType) {

            case PAWN:
                return new PawnMovingRule();
            case KING:
                break;
            case QUEEN:
                break;
            case BISHOP:
                break;
            case KNIGHT:
                break;
            case ROOK:
                break;
        }
        return null;
    }
}
