package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.moving.RBQValidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.*;

import static app.domain.moving.MoveDescriber.ALL_MOVE_DESCRIBERS;

public class KingMovingRule implements MovingRule {

    private static final Integer KING_LIMIT_POSITIONS = 1;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();

    public KingMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMovesList = Arrays.asList(
                new Tuple<>(new ForwardMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalLeft(), KING_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), KING_LIMIT_POSITIONS));
        this.moveParameters.put(PieceColor.WHITE, legalMovesList);
        this.moveParameters.put(PieceColor.BLACK, legalMovesList);
    }

    @Override
    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Map<MoveDescriber, Collection<Position>> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        return validator.keepValidPositions(chessBoard, moveSettings, possiblePositions);
    }

    @Override
    public Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<MoveDescriber>> getCapturingMoves() {
        Map<PieceColor, Collection<MoveDescriber>> capturingMoves = new HashMap<>();
        capturingMoves.put(PieceColor.WHITE, ALL_MOVE_DESCRIBERS);
        capturingMoves.put(PieceColor.BLACK, ALL_MOVE_DESCRIBERS);
        return capturingMoves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.KING;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }
}
