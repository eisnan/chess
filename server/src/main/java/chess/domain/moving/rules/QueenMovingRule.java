package chess.domain.moving.rules;

import chess.domain.Piece;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.moving.validators.RBQValidator;
import chess.domain.util.Pair;
import chess.domain.moving.moves.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QueenMovingRule implements MovingRule {

    private static final Integer QUEEN_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Pair<Move, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();

    private final Collection<Pair<Move, Integer>> legalMoves = Arrays.asList(
            new Pair<>(new ForwardMove(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new BackwardMove(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new LeftMove(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new RightMove(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new ForwardDiagonalLeft(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new ForwardDiagonalRight(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new BackwardDiagonalLeft(), QUEEN_LIMIT_POSITIONS),
            new Pair<>(new BackwardDiagonalRight(), QUEEN_LIMIT_POSITIONS));

    public QueenMovingRule() {
        moveParameters.put(PieceColor.WHITE, legalMoves);
        moveParameters.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Map<PieceColor, Collection<Pair<Move, Integer>>> getMoveParameters() {
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
    public Collection<Move> getMoveDescribers() {
        return null;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }
}
