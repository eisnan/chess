package chess.domain.moving.rules;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.moving.moves.Move;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.moving.validators.RBQValidator;
import chess.domain.moving.moves.BackwardMove;
import chess.domain.moving.moves.ForwardMove;
import chess.domain.moving.moves.LeftMove;
import chess.domain.moving.moves.RightMove;
import chess.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RookMovingRule implements MovingRule {

    private static final int ROOK_LIMIT_POSITIONS = 8;

    private PositionValidator validator = new RBQValidator();
    private Map<PieceColor, Collection<Tuple<Move, Integer>>> moveParameters = new HashMap<>();

    public RookMovingRule() {
        Collection<Tuple<Move, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), ROOK_LIMIT_POSITIONS));
        moveParameters.put(PieceColor.WHITE, legalMoves);
        moveParameters.put(PieceColor.BLACK, legalMoves);
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
        return PieceType.ROOK;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<Move> getMoveDescribers() {
        return null;
    }
}
