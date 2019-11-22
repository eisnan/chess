package chess.domain;

import chess.domain.moving.rules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

public enum PieceType {
    PAWN("", new PawnMovingRule()), KING("K", new KingMovingRule()), QUEEN("Q", new QueenMovingRule()),
    BISHOP("B", new BishopMovingRule()), KNIGHT("N", new KnightMovingRule()), ROOK("R", new RookMovingRule());

    private String notationSymbol;
    private MovingRule movingRule;

    PieceType(String notationSymbol, MovingRule movingRule) {
        this.notationSymbol = notationSymbol;
        this.movingRule = movingRule;
    }

    public String getTypeNotation() {
        return notationSymbol;
    }

    public MovingRule getMovingRule() {
        return movingRule;
    }

    public static PieceType getByNotationSymbol(String notationSymbol) {
        for (PieceType pieceType : PieceType.values()) {
            if (notationSymbol.equals(pieceType.getTypeNotation())) {
                return pieceType;
            }
        }
        //for pawns should be rank
        for (File f : File.values()) {
            if (notationSymbol.equals(f.name())) {
                return PAWN;
            }
        }
        throw new IllegalArgumentException();
    }

}
