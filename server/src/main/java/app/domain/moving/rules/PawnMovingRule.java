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

    public PawnMovingRule() {
        moveParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardMove(), 2),
                new Tuple<>(new ForwardDiagonalLeft(), 1),
                new Tuple<>(new ForwardDiagonalRight(), 1)));
        moveParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardMove(), 2),
                new Tuple<>(new BackwardDiagonalLeft(), 1),
                new Tuple<>(new BackwardDiagonalRight(), 1)));
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
        capturingMoves.put(PieceColor.WHITE, Arrays.asList(
                new ForwardDiagonalLeft(),
                new ForwardDiagonalRight()));
        capturingMoves.put(PieceColor.BLACK, Arrays.asList(
                new BackwardDiagonalLeft(),
                new BackwardDiagonalRight()));
        return capturingMoves;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }


}
