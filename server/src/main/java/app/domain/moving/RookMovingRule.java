package app.domain.moving;

import app.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RookMovingRule implements MovingRule {

    private static final int ROOK_LIMIT_POSITIONS = 8;

    @Override
    public List<Position> getPossiblePositions(Piece piece, Position currentPosition) {

        List<Position> availableMoves = getAvailableMoves(getMoveSettings(currentPosition, piece));


        return availableMoves;
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

//        List<Position> validPositions = positions.stream().filter(position -> checkPositionNotToBeOccupiedBySameColor(position, piece)).collect(Collectors.toList());
        List<Position> validPositions = new ArrayList<>();
        switch (moveType) {

            case FORWARD:
                return positions.stream().filter(position -> {
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : true);
                }).collect(Collectors.toList());
            case BACKWARD:
                for (Position position : positions) {
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    if (piece == null) {
                        validPositions.add(position);
                    }
                }
                break;
            case LEFT:
                return positions.stream().filter(position -> {
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : true);
                }).collect(Collectors.toList());
            case RIGHT:

        }
        return validPositions;
    }
}
