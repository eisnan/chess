package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.moving.RBQValidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.*;

import static app.domain.moving.MoveDescriber.DIAGONAL_MOVE_DESCRIBERS;

public class BishopMovingRule implements MovingRule {

    private static final Integer BISHOP_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();

    public BishopMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardDiagonalLeft(), BISHOP_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), BISHOP_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), BISHOP_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), BISHOP_LIMIT_POSITIONS));
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
        capturingMoves.put(PieceColor.WHITE, DIAGONAL_MOVE_DESCRIBERS);
        capturingMoves.put(PieceColor.BLACK, DIAGONAL_MOVE_DESCRIBERS);
        return capturingMoves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }
}
