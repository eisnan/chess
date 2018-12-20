package app.domain.moving.rules;

import app.domain.PieceType;

public class MovingRules {
    public static MovingRule getMovingRule(PieceType pieceType) {
        switch (pieceType) {

            case PAWN:
                return new PawnMovingRule();
            case KING:
                return new KingMovingRule();
            case QUEEN:
                return new QueenMovingRule();
            case BISHOP:
                return new BishopMovingRule();
            case KNIGHT:
                return new KnightMovingRule();

            case ROOK:
                return new RookMovingRule();

            default:
                throw new RuntimeException();
        }
    }
}
