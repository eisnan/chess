package app.domain.moving.rules;

import app.domain.PieceColor;
import app.domain.PieceType;
import app.domain.moving.moves.Move;
import app.domain.moving.NValidator;
import app.domain.moving.validators.PositionValidator;
import app.domain.moving.moves.KnightMove;
import app.domain.util.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class KnightMovingRule implements MovingRule {

    private PositionValidator validator = new NValidator();
    private Map<PieceColor, Collection<Tuple<Move, Integer>>> moveParameters = new HashMap<>();
    private Move legalMove = new KnightMove();

    public KnightMovingRule() {
        moveParameters.put(PieceColor.WHITE, Collections.singletonList(new Tuple<>(legalMove, 0)));
        moveParameters.put(PieceColor.BLACK, Collections.singletonList(new Tuple<>(legalMove, 0)));
    }

    @Override
    public Map<PieceColor, Collection<Tuple<Move, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<Tuple<Move, Integer>>> getCaptureParameters() {
        return moveParameters;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<Move> getMoveDescribers() {
        return Collections.singletonList(legalMove);
    }
}
