package app.domain.moving.rules;

import app.domain.PieceColor;
import app.domain.PieceType;
import app.domain.moving.moves.*;
import app.domain.moving.validators.PositionValidator;
import app.domain.moving.validators.RBQValidator;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KingMovingRule implements MovingRule {

    private static final Integer KING_LIMIT_POSITIONS = 1;
    private static final Integer CASTLING_POSITIONS = 2;
    private Map<PieceColor, Collection<Tuple<Move, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();
    private Collection<Move> legalMoves = Arrays.asList(
            new ForwardMove(),
            new BackwardMove(),
            new LeftMove(),
            new RightMove(),
            new ForwardDiagonalLeft(),
            new ForwardDiagonalRight(),
            new BackwardDiagonalLeft(),
            new BackwardDiagonalRight(),
            new QueenSideCastling(),
            new KingSideCastling());

    public KingMovingRule() {
        Collection<Tuple<Move, Integer>> legalMovesWithLimit = legalMoves
                .stream().map(moveDescriber -> new Tuple<>(moveDescriber, getLimitPositions(moveDescriber))).collect(Collectors.toList());
        this.moveParameters.put(PieceColor.WHITE, legalMovesWithLimit);
        this.moveParameters.put(PieceColor.BLACK, legalMovesWithLimit);
    }

    private Integer getLimitPositions(Move moveDescriber) {
        return moveDescriber instanceof IterableMove ? KING_LIMIT_POSITIONS : moveDescriber instanceof SpecialMove ? CASTLING_POSITIONS : 0;
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
        return PieceType.KING;
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
