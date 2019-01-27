package app.domain.moving.rules;

import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.PieceType;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.validators.PositionValidator;
import app.domain.moving.validators.RBQValidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QueenMovingRule implements MovingRule {

    private static final Integer QUEEN_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();

    public QueenMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalLeft(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), QUEEN_LIMIT_POSITIONS));
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
        return PieceType.QUEEN;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<MoveDescriber> getMoveDescribers() {
        return null;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }
}
