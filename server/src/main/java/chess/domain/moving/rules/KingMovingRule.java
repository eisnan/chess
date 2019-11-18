package chess.domain.moving.rules;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.moving.moves.*;
import chess.domain.moving.validators.KValidator;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KingMovingRule implements MovingRule {

    private static final Integer KING_LIMIT_POSITIONS = 1;
    private static final Integer CASTLING_POSITIONS = 2;
    private Map<PieceColor, Collection<Pair<Move, Integer>>> moveParameters = new HashMap<>();
    private Map<PieceColor, Collection<Pair<Move, Integer>>> captureParameters = new HashMap<>();

    private PositionValidator validator = new KValidator();
    private Collection<Move> iterableMoves = Arrays.asList(
            new ForwardMove(),
            new BackwardMove(),
            new LeftMove(),
            new RightMove(),
            new ForwardDiagonalLeft(),
            new ForwardDiagonalRight(),
            new BackwardDiagonalLeft(),
            new BackwardDiagonalRight());

    private Collection<Move> specialMoves = Arrays.asList(
            new QueenSideCastling(),
            new KingSideCastling());

    public KingMovingRule() {
        Collection<Pair<Move, Integer>> iterables = iterableMoves
                .stream().map(moveDescriber -> new Pair<>(moveDescriber, getLimitPositions(moveDescriber))).collect(Collectors.toList());
        this.moveParameters.put(PieceColor.WHITE, iterables);
        this.moveParameters.put(PieceColor.BLACK, iterables);

        Collection<Pair<Move, Integer>> specials = specialMoves
                .stream().map(moveDescriber -> new Pair<>(moveDescriber, getLimitPositions(moveDescriber))).collect(Collectors.toList());

        this.captureParameters.put(PieceColor.WHITE, specials);
        this.captureParameters.put(PieceColor.BLACK, specials);
    }

    private Integer getLimitPositions(Move moveDescriber) {
        return moveDescriber instanceof IterableMove ? KING_LIMIT_POSITIONS : moveDescriber instanceof SpecialMove ? CASTLING_POSITIONS : 0;
    }

    @Override
    public Map<PieceColor, Collection<Pair<Move, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<Pair<Move, Integer>>> getCaptureParameters() {
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
        return iterableMoves;
    }
}
