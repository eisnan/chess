package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.moves.BackwardMove;
import app.domain.moving.moves.ForwardMove;
import app.domain.moving.moves.LeftMove;
import app.domain.moving.moves.RightMove;
import app.domain.util.Tuple;

import java.util.*;

public class RookMovingRule implements MovingRule {

    private static final int ROOK_LIMIT_POSITIONS = 8;

    private PositionInvalidator invalidator = new RBQInvalidator();
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings = new HashMap<>();

    public RookMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), ROOK_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), ROOK_LIMIT_POSITIONS));
        moveSettings.put(PieceColor.WHITE, legalMoves);
        moveSettings.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));
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

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        return invalidator.invalidate(chessBoard, currentPosition, selectedPiece, positions);
    }
}
