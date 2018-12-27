package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.moving.RBQValidator;
import app.domain.moving.moves.BackwardMove;
import app.domain.moving.moves.ForwardMove;
import app.domain.moving.moves.LeftMove;
import app.domain.moving.moves.RightMove;
import app.domain.util.Tuple;

import java.util.*;
import java.util.stream.Collectors;

import static app.domain.moving.MoveDescriber.STRAIGHT_MOVE_DESCRIBERS;

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
    public Map<PieceColor, Collection<MoveDescriber>> getCapturingMoves() {
        Map<PieceColor, Collection<MoveDescriber>> capturingMoves = new HashMap<>();
        capturingMoves.put(PieceColor.WHITE, STRAIGHT_MOVE_DESCRIBERS);
        capturingMoves.put(PieceColor.BLACK, STRAIGHT_MOVE_DESCRIBERS);
        return capturingMoves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ROOK;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }
}
