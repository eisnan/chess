package app.domain.moving.rules;

import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.PieceType;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.validators.PositionValidator;
import app.domain.moving.validators.RBQValidator;
import app.domain.moving.moves.BackwardDiagonalLeft;
import app.domain.moving.moves.BackwardDiagonalRight;
import app.domain.moving.moves.ForwardDiagonalLeft;
import app.domain.moving.moves.ForwardDiagonalRight;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BishopMovingRule implements MovingRule {

    private static final Integer BISHOP_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();
    private Collection<MoveDescriber> legalMoves = Arrays.asList(
            new ForwardDiagonalLeft(),
            new ForwardDiagonalRight(),
            new BackwardDiagonalLeft(),
            new BackwardDiagonalRight());

    public BishopMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMovesWithLimit = legalMoves
                .stream().map(moveDescriber -> new Tuple<>(moveDescriber, BISHOP_LIMIT_POSITIONS)).collect(Collectors.toList());
        moveParameters.put(PieceColor.WHITE, legalMovesWithLimit);
        moveParameters.put(PieceColor.BLACK, legalMovesWithLimit);
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
        return PieceType.BISHOP;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<MoveDescriber> getMoveDescribers() {
        return legalMoves;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }
}
