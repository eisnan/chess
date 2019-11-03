package chess.domain.moving.rules;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.moving.moves.Move;
import chess.domain.moving.NValidator;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.moving.moves.KnightMove;
import chess.domain.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class KnightMovingRule implements MovingRule {

    private PositionValidator validator = new NValidator();
    private Map<PieceColor, Collection<Pair<Move, Integer>>> moveParameters = new HashMap<>();
    private Move legalMove = new KnightMove();

    public KnightMovingRule() {
        moveParameters.put(PieceColor.WHITE, Collections.singletonList(new Pair<>(legalMove, 0)));
        moveParameters.put(PieceColor.BLACK, Collections.singletonList(new Pair<>(legalMove, 0)));
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
