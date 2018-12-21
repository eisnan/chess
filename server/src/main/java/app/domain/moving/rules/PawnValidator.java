package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.*;
import app.domain.moving.moves.*;

import java.util.*;
import java.util.stream.Collectors;

public class PawnValidator implements PositionValidator {

    private Map<Class<? extends MoveDescriber>, SpecialMoveResolver> pawnSpecialMoves;

    public PawnValidator() {
        this.pawnSpecialMoves = new HashMap<>();
        this.pawnSpecialMoves.put(ForwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnSpecialMoves.put(ForwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnSpecialMoves.put(BackwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnSpecialMoves.put(BackwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnSpecialMoves.put(ForwardMove.class, new PawnStraightMoveResolver());
        this.pawnSpecialMoves.put(BackwardMove.class, new PawnStraightMoveResolver());

    }

    @Override
    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        List<Position> validPositions = new ArrayList<>();

        Piece selectedPiece = moveSettings.getPiece();
        Position currentPosition = moveSettings.getCurrentPosition();

        possiblePositions.forEach((moveDescriber, positions) -> {

            SpecialMoveResolver specialMoveResolver = pawnSpecialMoves.get(moveDescriber.getClass());

            validPositions.addAll(specialMoveResolver.validate(chessBoard,moveSettings, selectedPiece.getPieceColor(),  positions));

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
