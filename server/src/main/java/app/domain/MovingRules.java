package app.domain;

public class MovingRules {
    public static MovingRule getMovingRule(PieceType pieceType) {
        switch (pieceType) {

            case PAWN:
                return new PawnMovingRule();
            case KING:
                return new PawnMovingRule();
            case QUEEN:
                return new PawnMovingRule();
            case BISHOP:
                return new PawnMovingRule();
            case KNIGHT:
                return new PawnMovingRule();

            case ROOK:
                return new PawnMovingRule();

            default:
                return new PawnMovingRule();
        }
    }
}
