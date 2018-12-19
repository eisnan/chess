package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.*;

public class KingMovingRule implements MovingRule {

    private static final Integer KING_LIMIT_POSITIONS = 1;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings = new HashMap<>();
    private PositionInvalidator invalidator = new RBQInvalidator();

    public KingMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalLeft(), KING_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), KING_LIMIT_POSITIONS));
        moveSettings.put(PieceColor.WHITE, legalMoves);
        moveSettings.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));
    }

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        return invalidator.invalidate(chessBoard, currentPosition, selectedPiece, positions);

    }


    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adapt(piece.getPieceColor(), moveSettings));
    }

    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMaxLimit().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
    }
}
