package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RookMovingRule implements MovingRule {

    private static final int ROOK_LIMIT_POSITIONS = 8;

    private PositionInvalidator invalidator = new RBQInvalidator();

    @Override
    public List<Position> getPossiblePositions(Piece piece, Position currentPosition) {
        return getAvailableMoves(getMoveSettings(currentPosition, piece));
    }


    private List<Position> getAvailableMoves(MoveSettings moveSettings) {
        List<Position> positions = MoveType.FORWARD.checkMove(moveSettings);
        positions.addAll(MoveType.BACKWARD.checkMove(moveSettings));
        positions.addAll(MoveType.LEFT.checkMove(moveSettings));
        positions.addAll(MoveType.RIGHT.checkMove(moveSettings));
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
    public List<Position> removeInvalidPositions(MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions) {
        return invalidator.invalidate(currentPosition, selectedPiece, positions);
    }
}
