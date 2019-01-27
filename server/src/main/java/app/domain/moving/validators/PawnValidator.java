package app.domain.moving.validators;

import app.domain.*;
import app.domain.moving.*;
import app.domain.moving.moves.*;

import java.util.*;

public class PawnValidator implements PositionValidator {

    private Map<Class<? extends MoveDescriber>, MoveResolver> pawnMoves;
    private Map<Class<? extends MoveDescriber>, MoveResolver> pawnCaptures;

    public PawnValidator() {
        this.pawnMoves = new HashMap<>();
        this.pawnMoves.put(ForwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(ForwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(BackwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(BackwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnMoves.put(ForwardMove.class, new PawnStraightMoveResolver());
        this.pawnMoves.put(BackwardMove.class, new PawnStraightMoveResolver());
        this.pawnCaptures = new HashMap<>();
        this.pawnCaptures.put(ForwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnCaptures.put(ForwardDiagonalRight.class, new PawnDiagonalMoveResolver());
        this.pawnCaptures.put(BackwardDiagonalLeft.class, new PawnDiagonalMoveResolver());
        this.pawnCaptures.put(BackwardDiagonalRight.class, new PawnDiagonalMoveResolver());

    }

    @Override
    public Collection<Position> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        Collection<Position> validPositions = new TreeSet<>(new AscendingPositionComparator());
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            MoveResolver moveResolver = pawnMoves.get(moveDescriber.getClass());
            validPositions.addAll(moveResolver.validate(chessBoard, moveDescriber, moveSettings, selectedPiece.getPieceColor(), positions));
        });
        return validPositions;
    }

    @Override
    public Collection<Position> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        Collection<Position> validAttackingPositions = new TreeSet<>();
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            MoveResolver moveResolver = pawnCaptures.get(moveDescriber.getClass());
            validAttackingPositions.addAll(moveResolver.validate(chessBoard, moveDescriber, moveSettings, selectedPiece.getPieceColor(), positions));
        });
        return validAttackingPositions;
    }


    public static boolean isEnPassant(ChessBoard chessBoard, Piece currentPiece, Position currentPosition, Position evaluatedPosition) {
        Move lastMove;
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
