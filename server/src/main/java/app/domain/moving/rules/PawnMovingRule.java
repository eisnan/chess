package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.Move2;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.*;
import java.util.stream.Collectors;

public class PawnMovingRule implements MovingRule {

//    private PositionInvalidator invalidator = new PInvalidator();

    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings = new HashMap<>();

    public PawnMovingRule() {
        moveSettings.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardMove(), 2),
                new Tuple<>(new ForwardDiagonalLeft(), 1),
                new Tuple<>(new ForwardDiagonalRight(), 1)));
        moveSettings.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardMove(), 2),
                new Tuple<>(new BackwardDiagonalLeft(), 1),
                new Tuple<>(new BackwardDiagonalRight(), 1)));
    }

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {


        Collection<Position> availableMoves = getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));


        return availableMoves;
    }

    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMaxSquares().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adapt(piece.getPieceColor(), moveSettings));
    }

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        List<Position> validPositions = new ArrayList<>();

        switch (selectedPiece.getPieceColor()) {

            case WHITE:
                if (moveDescriber instanceof ForwardDiagonalLeft) {
                    return positions.stream().filter(position -> {
                        Piece piece = chessBoard.getModel().get(position);
                        return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor() && piece.canBeCaptured()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
                    }).collect(Collectors.toList());
                }
                if (moveDescriber instanceof ForwardMove) {
                    for (Position position : positions) {
                        Piece piece = chessBoard.getModel().get(position);
                        if (piece == null) {
                            validPositions.add(position);
                            if (currentPosition.getRank() != Rank._2) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (moveDescriber instanceof ForwardDiagonalRight) {
                    return positions.stream().filter(position -> {
                        Piece piece = chessBoard.getModel().get(position);
                        return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor() && piece.canBeCaptured()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
                    }).collect(Collectors.toList());
                }
                break;
            case BLACK:
                if (moveDescriber instanceof BackwardDiagonalRight) {
                    return positions.stream().filter(position -> {
                        Piece piece = chessBoard.getModel().get(position);
                        return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor() && piece.canBeCaptured()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
                    }).collect(Collectors.toList());
                }
                if (moveDescriber instanceof BackwardMove) {
                    for (Position position : positions) {
                        Piece piece = chessBoard.getModel().get(position);
                        if (piece == null) {
                            validPositions.add(position);
                            if (currentPosition.getRank() != Rank._7) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (moveDescriber instanceof BackwardDiagonalLeft) {
                    return positions.stream().filter(position -> {
                        Piece piece = chessBoard.getModel().get(position);
                        return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor() && piece.canBeCaptured()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
                    }).collect(Collectors.toList());
                }
                break;
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
