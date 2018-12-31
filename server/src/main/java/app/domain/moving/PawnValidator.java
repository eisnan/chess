package app.domain.moving;

import app.domain.*;
import app.domain.moving.moves.*;

import java.util.*;

public class PawnValidator implements PositionValidator {

    private Map<Class<? extends MoveDescriber>, MoveResolver> pawnMoves;

    public PawnValidator() {
        this.pawnMoves = new HashMap<>();
        this.pawnMoves.put(ForwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(ForwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(BackwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(BackwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(ForwardMove.class, new PawnStraightMoveResolver());
        this.pawnMoves.put(BackwardMove.class, new PawnStraightMoveResolver());

    }

    @Override
    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        List<Position> validPositions = new ArrayList<>();

        Piece selectedPiece = moveSettings.getPiece();
        Position currentPosition = moveSettings.getCurrentPosition();

        possiblePositions.forEach((moveDescriber, positions) -> {

            MoveResolver moveResolver = pawnMoves.get(moveDescriber.getClass());

            validPositions.addAll(moveResolver.validate(chessBoard, moveDescriber, moveSettings, selectedPiece.getPieceColor(), positions));

//
//                           switch (selectedPiece.getPieceColor()) {
//
//                case WHITE:
//                    if (moveDescriber instanceof ForwardDiagonalLeft) {
//                        Set<Position> collected = positions.stream().filter(position -> {
//                            Piece piece = chessBoard.getModel().get(position);
//                            return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
//                        }).collect(Collectors.toSet());
//                        validPositions.addAll(collected);
//                        break;
//                    }
//                    if (moveDescriber instanceof ForwardMove) {
//                        for (Position position : positions) {
//                            Piece piece = chessBoard.getModel().get(position);
//                            if (piece == null) {
//                                validPositions.add(position);
//                                if (currentPosition.getRank() != Rank._2) {
//                                    break;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        break;
//                    }
//                    if (moveDescriber instanceof ForwardDiagonalRight) {
//                        List<Position> collected = positions.stream().filter(position -> {
//                            Piece piece = chessBoard.getModel().get(position);
//                            return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
//                        }).collect(Collectors.toList());
//                        validPositions.addAll(collected);
//                        break;
//                    }
//                    break;
//                case BLACK:
//                    if (moveDescriber instanceof BackwardDiagonalRight) {
//                        List<Position> collected = positions.stream().filter(position -> {
//                            Piece piece = chessBoard.getModel().get(position);
//                            return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
//                        }).collect(Collectors.toList());
//                        validPositions.addAll(collected);
//                    }
//                    if (moveDescriber instanceof BackwardMove) {
//                        for (Position position : positions) {
//                            Piece piece = chessBoard.getModel().get(position);
//                            if (piece == null) {
//                                validPositions.add(position);
//                                if (currentPosition.getRank() != Rank._7) {
//                                    break;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                    }
//                    if (moveDescriber instanceof BackwardDiagonalLeft) {
//                        List<Position> collected = positions.stream().filter(position -> {
//                            Piece piece = chessBoard.getModel().get(position);
//                            return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : isEnPassant(chessBoard, selectedPiece, currentPosition, position));
//                        }).collect(Collectors.toList());
//                        validPositions.addAll(collected);
//                    }
//                    break;
//            }
        });
        return validPositions;
    }


    public static boolean isEnPassant(ChessBoard chessBoard, Piece currentPiece, Position currentPosition, Position evaluatedPosition) {
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
