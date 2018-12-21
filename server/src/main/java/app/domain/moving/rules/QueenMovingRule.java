package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionInvalidator;
import app.domain.moving.RBQInvalidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.*;

public class QueenMovingRule implements MovingRule {

    private static final Integer QUEEN_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings = new HashMap<>();
    private PositionInvalidator invalidator = new RBQInvalidator();

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
        moveSettings.put(PieceColor.WHITE, legalMoves);
        moveSettings.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));
    }

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
//        return invalidator.invalidate(chessBoard, currentPosition, selectedPiece, positions);
        return invalidator.invalidatePositions();

    }


    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adapt(piece.getPieceColor(), moveSettings));
    }

    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMaxSquares().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
    }
}
