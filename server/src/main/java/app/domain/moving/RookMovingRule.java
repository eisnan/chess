package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RookMovingRule implements MovingRule {

    private static final int ROOK_LIMIT_POSITIONS = 8;

    private PositionInvalidator invalidator = new RBQInvalidator();

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));
    }


    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = MoveType.FORWARD.checkMove(chessBoard, moveSettings);
        positions.addAll(MoveType.BACKWARD.checkMove(chessBoard, moveSettings));
        positions.addAll(MoveType.LEFT.checkMove(chessBoard, moveSettings));
        positions.addAll(MoveType.RIGHT.checkMove(chessBoard, moveSettings));
        return positions;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        Map<MoveType, Integer> limitPerMoveType = new HashMap<>();
        limitPerMoveType.put(MoveType.FORWARD, 8);
        limitPerMoveType.put(MoveType.BACKWARD, 8);
        limitPerMoveType.put(MoveType.LEFT, 8);
        limitPerMoveType.put(MoveType.RIGHT, ROOK_LIMIT_POSITIONS);
        return new MoveSettings(currentPosition, piece, this, limitPerMoveType);

    }

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        return invalidator.invalidate(chessBoard, currentPosition, selectedPiece, positions);
    }
}
