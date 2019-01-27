package app.domain.moving.rules;

import app.domain.PieceType;
import app.domain.moving.moves.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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

    public static Collection<MovingRule> getAllMovingRules() {
        return Arrays.asList(new PawnMovingRule(), new KingMovingRule(), new QueenMovingRule(), new BishopMovingRule(), new KnightMovingRule(), new RookMovingRule());
    }


    public static Collection<MovingRule> getAllMovingRulesForCheck() {
        return Arrays.asList(new PawnMovingRule(), new QueenMovingRule(), new BishopMovingRule(), new KnightMovingRule(), new RookMovingRule());
    }

    public static Collection<PieceType> findWhichPiecesCanAttackOnThisDirection(Move move) {
        Collection<PieceType> pieceTypes = new ArrayList<>();


        return pieceTypes;

    }
}
