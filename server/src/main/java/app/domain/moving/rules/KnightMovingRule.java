package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.NValidator;
import app.domain.moving.PositionValidator;
import app.domain.moving.moves.KnightMove;
import app.domain.util.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class KnightMovingRule implements MovingRule {

    private PositionValidator validator = new NValidator();
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();

    public KnightMovingRule() {
        moveParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new KnightMove(), 0)));
        moveParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new KnightMove(), 0)));
    }

    @Override
    public Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<MoveDescriber>> getCapturingMoves() {
        Map<PieceColor, Collection<MoveDescriber>> capturingMoves = new HashMap<>();
        capturingMoves.put(PieceColor.WHITE, Arrays.asList(new KnightMove()));
        capturingMoves.put(PieceColor.BLACK, Arrays.asList(new KnightMove()));
        return capturingMoves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KNIGHT;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }
}
