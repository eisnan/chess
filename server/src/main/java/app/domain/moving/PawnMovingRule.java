package app.domain.moving;

import app.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PawnMovingRule implements MovingRule {

    private PositionInvalidator invalidator = new PInvalidator();

    @Override
    public List<Position> getPossiblePositions(Piece piece, Position currentPosition) {


        List<Position> availableMoves = getAvailableMoves(getMoveSettings(currentPosition, piece));


        return availableMoves;
    }

    private List<Position> getAvailableMoves(MoveSettings moveSettings) {


        List<Position> positions = MoveType.FORWARD_DIAGONAL_LEFT.checkMove(moveSettings);
        positions.addAll(MoveType.FORWARD.checkMove(moveSettings));
        positions.addAll(MoveType.FORWARD_DIAGONAL_RIGHT.checkMove(moveSettings));
        return positions;
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        Map<MoveType, Integer> limitPerMoveType = new HashMap<>();
        limitPerMoveType.put(MoveType.FORWARD_DIAGONAL_LEFT, 1);
        limitPerMoveType.put(MoveType.FORWARD_DIAGONAL_RIGHT, 1);
        if ((currentPosition.getRank() == Rank._2 && piece.getPieceColor() == PieceColor.WHITE) || currentPosition.getRank() == Rank._7 && piece.getPieceColor() == PieceColor.BLACK) {
            limitPerMoveType.put(MoveType.FORWARD, 2);
        } else {
            limitPerMoveType.put(MoveType.FORWARD, 1);
        }
        return new MoveSettings(currentPosition, piece, this, limitPerMoveType);
    }

    @Override
    public List<Position> removeInvalidPositions(MoveType moveType, Position currentPosition, Piece selectedPiece, List<Position> positions) {
        List<Position> validPositions = new ArrayList<>();
        switch (moveType) {

            case FORWARD_DIAGONAL_LEFT:
                //capturing move
                // TODO EP move
                return positions.stream().filter(position -> {
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(selectedPiece, currentPosition, position));
                }).collect(Collectors.toList());
            case FORWARD:
                // move
                for (Position position : positions) {
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    if (piece == null) {
                        validPositions.add(position);
                    } else {
                        break;
                    }
                }
                break;
            case FORWARD_DIAGONAL_RIGHT:
                //capturing move
                // TODO EP move
                return positions.stream().filter(position -> {
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(selectedPiece, currentPosition, position));
                }).collect(Collectors.toList());
        }
        return validPositions;
    }

    public boolean isEnPassant(Piece currentPiece, Position currentPosition, Position evaluatedPosition) {
        Move2 lastMove;
        boolean lastMoveMadeByPawn;
        boolean lastMoveWasDoubleStep;
        boolean sameFiles;
        switch (currentPiece.getPieceColor()) {
            case WHITE:

                lastMove = ChessBoard.INSTANCE.getLastMove();
                if (lastMove != null) {
                    lastMoveMadeByPawn = lastMove.getPiece().getPieceType() == PieceType.PAWN;
                    lastMoveWasDoubleStep = lastMove.getFromPosition().getRank().ordinal() - lastMove.getToPosition().getRank().ordinal() > 1;
                    sameFiles = lastMove.getToPosition().getFile() == evaluatedPosition.getFile();
                    return currentPosition.getRank() == Rank._5 && lastMoveMadeByPawn && lastMoveWasDoubleStep && sameFiles;
                }
                break;
            case BLACK:
                lastMove = ChessBoard.INSTANCE.getLastMove();
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

    //    @Override
    public boolean removeInvalidPositions(MoveType moveType, Position currentPosition, Position... positions) {

        for (Position position : positions) {
            switch (moveType) {

                case FORWARD_DIAGONAL_LEFT:
                    Piece piece = ChessBoard.INSTANCE.getModel().get(position);
                    return piece != null && (piece.getPieceColor() == PieceColor.BLACK);

                case FORWARD:
                    Piece piece1 = ChessBoard.INSTANCE.getModel().get(position);
                    return piece1 == null;
                case FORWARD_DIAGONAL_RIGHT:
                    Piece piece2 = ChessBoard.INSTANCE.getModel().get(position);
                    return piece2 != null && (piece2.getPieceColor() == PieceColor.BLACK);
            }
        }

        return false;
    }


    private boolean validPosition(Position position) {


        return false;
    }
}
