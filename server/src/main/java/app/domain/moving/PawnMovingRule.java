package app.domain.moving;

import app.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class PawnMovingRule implements MovingRule {

//    private PositionInvalidator invalidator = new PInvalidator();

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {


        Collection<Position> availableMoves = getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));


        return availableMoves;
    }

    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {


        Collection<Position> positions = new TreeSet<>();
        switch (moveSettings.getPiece().getPieceColor()) {
            case WHITE:
                positions.addAll(MoveType.FORWARD_DIAGONAL_LEFT.checkMove(chessBoard, moveSettings));
                positions.addAll(MoveType.FORWARD.checkMove(chessBoard, moveSettings));
                positions.addAll(MoveType.FORWARD_DIAGONAL_RIGHT.checkMove(chessBoard, moveSettings));
                return positions;
            case BLACK:
                positions.addAll(MoveType.BACKWARD_DIAGONAL_LEFT.checkMove(chessBoard, moveSettings));
                positions.addAll(MoveType.BACKWARD.checkMove(chessBoard, moveSettings));
                positions.addAll(MoveType.BACKWARD_DIAGONAL_RIGHT.checkMove(chessBoard, moveSettings));
                return positions;
        }

        return positions;
//        Collection<Position> positions = MoveType.FORWARD_DIAGONAL_LEFT.checkMove(chessBoard, moveSettings);
//        positions.addAll(MoveType.FORWARD.checkMove(chessBoard, moveSettings));
//        positions.addAll(MoveType.FORWARD_DIAGONAL_RIGHT.checkMove(chessBoard, moveSettings));
//        return positions;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        Map<MoveType, Integer> limitPerMoveType = new HashMap<>();

        switch (piece.getPieceColor()) {

            case WHITE:
                limitPerMoveType.put(MoveType.FORWARD_DIAGONAL_LEFT, 1);
                limitPerMoveType.put(MoveType.FORWARD_DIAGONAL_RIGHT, 1);
                if (currentPosition.getRank() == Rank._2) {
                    limitPerMoveType.put(MoveType.FORWARD, 2);
                } else {
                    limitPerMoveType.put(MoveType.FORWARD, 1);
                }
                break;
            case BLACK:
                limitPerMoveType.put(MoveType.BACKWARD_DIAGONAL_LEFT, 1);
                limitPerMoveType.put(MoveType.BACKWARD_DIAGONAL_RIGHT, 1);
                if (currentPosition.getRank() == Rank._7) {
                    limitPerMoveType.put(MoveType.BACKWARD, 2);
                } else {
                    limitPerMoveType.put(MoveType.BACKWARD, 1);
                }
                break;
        }
        return new MoveSettings(currentPosition, piece, this, limitPerMoveType);
    }

    @Override
    public List<Position> removeInvalidPositions(ChessBoard chessBoard, MoveType moveType, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        List<Position> validPositions = new ArrayList<>();
        switch (moveType) {

            case FORWARD_DIAGONAL_LEFT:
            case BACKWARD_DIAGONAL_RIGHT:
                //capturing move
                // TODO EP move
                return positions.stream().filter(position -> {
                    Piece piece = chessBoard.getModel().get(position);
                    return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
                }).collect(Collectors.toList());
            case FORWARD:
            case BACKWARD:
                // move
                for (Position position : positions) {
                    Piece piece = chessBoard.getModel().get(position);
                    if (piece == null) {
                        validPositions.add(position);
                    } else {
                        break;
                    }
                }
                break;
            case FORWARD_DIAGONAL_RIGHT:
            case BACKWARD_DIAGONAL_LEFT:
                //capturing move
                // TODO EP move
                return positions.stream().filter(position -> {
                    Piece piece = chessBoard.getModel().get(position);
                    return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
                }).collect(Collectors.toList());
        }
        return validPositions;
    }

    public boolean isEnPassant(ChessBoard chessBoard, Piece currentPiece, Position currentPosition, Position evaluatedPosition) {
        Move2 lastMove;
        boolean lastMoveMadeByPawn;
        boolean lastMoveWasDoubleStep;
        boolean sameFiles;
        switch (currentPiece.getPieceColor()) {
            case WHITE:

                lastMove = chessBoard.getLastMove();
                if (lastMove != null) {
                    lastMoveMadeByPawn = lastMove.getPiece().getPieceType() == PieceType.PAWN;
                    lastMoveWasDoubleStep = lastMove.getFromPosition().getRank().ordinal() - lastMove.getToPosition().getRank().ordinal() > 1;
                    sameFiles = lastMove.getToPosition().getFile() == evaluatedPosition.getFile();
                    return currentPosition.getRank() == Rank._5 && lastMoveMadeByPawn && lastMoveWasDoubleStep && sameFiles;
                }
                break;
            case BLACK:
                lastMove = chessBoard.getLastMove();
                if (lastMove != null) {
                    lastMoveMadeByPawn = lastMove.getPiece().getPieceType() == PieceType.PAWN;
                    lastMoveWasDoubleStep = lastMove.getFromPosition().getRank().ordinal() - lastMove.getToPosition().getRank().ordinal() < -1;
                    sameFiles = lastMove.getToPosition().getFile() == evaluatedPosition.getFile();
                    return currentPosition.getRank() == Rank._4 && lastMoveMadeByPawn && lastMoveWasDoubleStep && sameFiles;
                }
                break;
        }
        return false;
    }
}
