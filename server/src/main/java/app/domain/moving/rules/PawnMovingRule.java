package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PawnValidator;
import app.domain.moving.PositionValidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PawnMovingRule implements MovingRule {

    private PositionValidator validator = new PawnValidator();

    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> captureParameters = new HashMap<>();

    public PawnMovingRule() {
        moveParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardMove(), 2),
                new Tuple<>(new ForwardDiagonalLeft(), 1),
                new Tuple<>(new ForwardDiagonalRight(), 1)));
        moveParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardMove(), 2),
                new Tuple<>(new BackwardDiagonalLeft(), 1),
                new Tuple<>(new BackwardDiagonalRight(), 1)));

        captureParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardDiagonalLeft(), 1),
                new Tuple<>(new ForwardDiagonalRight(), 1)));
        captureParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardDiagonalLeft(), 1),
                new Tuple<>(new BackwardDiagonalRight(), 1)));

    }

    @Override
    public Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getMoveParameters() {
        return moveParameters;
    }

    @Override
    public Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getCaptureParameters() {
        return captureParameters;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    @Override
    public Collection<Position> getAttackingPositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {

        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Map<MoveDescriber, Collection<Position>> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        return validator.keepValidPositionsToMove(chessBoard, moveSettings, possiblePositions);

    }
}
