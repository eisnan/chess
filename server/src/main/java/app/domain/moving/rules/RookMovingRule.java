package app.domain.moving.rules;

import app.domain.PieceColor;
import app.domain.PieceType;
import app.domain.moving.MoveDescriber;
import app.domain.moving.validators.PositionValidator;
import app.domain.moving.validators.RBQValidator;
import app.domain.moving.moves.BackwardMove;
import app.domain.moving.moves.ForwardMove;
import app.domain.moving.moves.LeftMove;
import app.domain.moving.moves.RightMove;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RookMovingRule implements MovingRule {

    private static final int ROOK_LIMIT_POSITIONS = 8;

    private PositionValidator validator = new RBQValidator();
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();

    public RookMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), ROOK_LIMIT_POSITIONS));
        moveParameters.put(PieceColor.WHITE, legalMoves);
        moveParameters.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getCaptureParameters() {
        return moveParameters;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<MoveDescriber> getMoveDescribers() {
        return null;
    }
}
