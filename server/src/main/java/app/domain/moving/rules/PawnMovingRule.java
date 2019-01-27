package app.domain.moving.rules;

import app.domain.PieceColor;
import app.domain.PieceType;
import app.domain.moving.moves.*;
import app.domain.moving.validators.PawnValidator;
import app.domain.moving.validators.PositionValidator;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PawnMovingRule implements MovingRule {

    private PositionValidator validator = new PawnValidator();

    private Map<PieceColor, Collection<Tuple<Move, Integer>>> moveParameters = new HashMap<>();
    private Map<PieceColor, Collection<Tuple<Move, Integer>>> captureParameters = new HashMap<>();
    private Collection<Move> legalMoves = Arrays.asList(
            new ForwardMove(),
            new ForwardDiagonalLeft(),
            new ForwardDiagonalRight(),
            new BackwardMove(),
            new BackwardDiagonalLeft(),
            new BackwardDiagonalRight());

    public PawnMovingRule() {
        moveParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardMove(), 2)));
        moveParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardMove(), 2)));

        captureParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardDiagonalLeft(), 1),
                new Tuple<>(new ForwardDiagonalRight(), 1)));
        captureParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardDiagonalLeft(), 1),
                new Tuple<>(new BackwardDiagonalRight(), 1)));

    }

    @Override
    public Map<PieceColor, Collection<Tuple<Move, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<Tuple<Move, Integer>>> getCaptureParameters() {
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
