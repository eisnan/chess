package chess.domain.moving.rules;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.moving.moves.*;
import chess.domain.moving.validators.PawnValidator;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PawnMovingRule implements MovingRule {

    private PositionValidator validator = new PawnValidator();

    private Map<PieceColor, Collection<Pair<Move, Integer>>> moveParameters = new HashMap<>();
    private Map<PieceColor, Collection<Pair<Move, Integer>>> captureParameters = new HashMap<>();
    private Collection<Move> legalMoves = Arrays.asList(
            new ForwardMove(),
            new ForwardDiagonalLeft(),
            new ForwardDiagonalRight(),
            new BackwardMove(),
            new BackwardDiagonalLeft(),
            new BackwardDiagonalRight(),
            new PromotionMove());

    public PawnMovingRule() {
        moveParameters.put(PieceColor.WHITE, Arrays.asList(new Pair<>(new ForwardMove(), 2)));
        moveParameters.put(PieceColor.BLACK, Arrays.asList(new Pair<>(new BackwardMove(), 2)));

        captureParameters.put(PieceColor.WHITE, Arrays.asList(new Pair<>(new ForwardDiagonalLeft(), 1),
                new Pair<>(new ForwardDiagonalRight(), 1)));
        captureParameters.put(PieceColor.BLACK, Arrays.asList(new Pair<>(new BackwardDiagonalLeft(), 1),
                new Pair<>(new BackwardDiagonalRight(), 1)));

    }

    @Override
    public Map<PieceColor, Collection<Pair<Move, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<Pair<Move, Integer>>> getCaptureParameters() {
        return captureParameters;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<Move> getMoveDescribers() {
        return legalMoves;
    }
}
