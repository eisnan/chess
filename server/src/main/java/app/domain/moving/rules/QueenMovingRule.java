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

public class QueenMovingRule implements MovingRule {

    private static final Integer QUEEN_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();
    private PositionValidator validator = new RBQValidator();

    public QueenMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalLeft(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), QUEEN_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), QUEEN_LIMIT_POSITIONS));
        moveParameters.put(PieceColor.WHITE, legalMoves);
        moveParameters.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));
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
        return PieceType.QUEEN;
    }

    @Override
    public PositionValidator getValidator() {
        return validator;
    }

    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
//        return validator.invalidate(chessBoard, currentPosition, selectedPiece, positions);
        return validator.keepValidPositions(chessBoard, getMoveSettings(currentPosition, selectedPiece), null);

    }


    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }

    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMovingSettings().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
    }
}
