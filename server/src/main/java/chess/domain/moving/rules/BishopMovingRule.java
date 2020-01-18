package chess.domain.moving.rules;

import chess.domain.Piece;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.moves.*;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.moving.validators.RBQValidator;
import chess.domain.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class BishopMovingRule implements MovingRule {

    private static final Integer BISHOP_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Pair<Move, Integer>>> moveParameters = new EnumMap<>(PieceColor.class);
    private PositionValidator validator = new RBQValidator();
    private Collection<Move> legalMoves = Arrays.asList(
            new ForwardDiagonalLeft(),
            new ForwardDiagonalRight(),
            new BackwardDiagonalLeft(),
            new BackwardDiagonalRight());

    public BishopMovingRule() {
        Collection<Pair<Move, Integer>> legalMovesWithLimit = legalMoves
                .stream().map(moveDescriber -> new Pair<>(moveDescriber, BISHOP_LIMIT_POSITIONS)).collect(Collectors.toList());
        moveParameters.put(PieceColor.WHITE, legalMovesWithLimit);
        moveParameters.put(PieceColor.BLACK, legalMovesWithLimit);
    }

    @Override
    public Map<PieceColor, Collection<Pair<Move, Integer>>> getMoveParameters() {
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
    public Collection<Move> getMoveDescribers() {
        return legalMoves;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }
}
