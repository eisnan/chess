package chess.domain.moving.rules;

import chess.domain.*;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.SpecialMove;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import static chess.domain.PieceColor.WHITE;

public class EnPassantMove implements SpecialMove {

    private Comparator<PlayerMove> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<PlayerMove> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == WHITE ? whitePositionComparator : blackPositionComparator;
    }

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        PlayerMove lastPlayerMove = chessBoard.getLastMove();
        if (lastPlayerMove == null) {
            return Collections.emptySet();
        }

        Position currentPosition = moveSettings.getCurrentPosition();
        Piece currentPiece = moveSettings.getPiece();
        if (currentPositionEpRank(currentPiece.getPieceColor(), currentPosition)
                && lastMoveDoubleStepPawn(lastPlayerMove)
                && adjacentFiles(lastPlayerMove.getToPosition(), currentPosition)) {
            Position epPosition;
            switch (currentPiece.getPieceColor()) {
                case WHITE:
                    epPosition = Position.ofValid(lastPlayerMove.getToPosition().getFile(), Rank._6);
                    return Collections.singleton(new PlayerMove(currentPiece, currentPosition, epPosition, MoveType.EN_PASSANT));
                case BLACK:
                    epPosition = Position.ofValid(lastPlayerMove.getToPosition().getFile(), Rank._3);
                    return Collections.singleton(new PlayerMove(currentPiece, currentPosition, epPosition, MoveType.EN_PASSANT));
            }

        }

        return Collections.emptySet();
    }


    private boolean currentPositionEpRank(PieceColor pieceColor, Position currentPosition) {
        switch (pieceColor) {
            case WHITE:
                return currentPosition.getRank() == Rank._5;
            case BLACK:
                return currentPosition.getRank() == Rank._4;
        }
        return false;
    }

    private boolean adjacentFiles(Position lastMoveToPosition, Position currentPosition) {
        return File.areAdjacent(lastMoveToPosition.getFile(), currentPosition.getFile());
    }

    private boolean lastMoveDoubleStepPawn(PlayerMove lastPlayerMove) {
        boolean lastMoveMadeByPawn = lastPlayerMove.getPiece().getPieceType() == PieceType.PAWN;
        boolean lastMoveWasDoubleStep = Math.abs(lastPlayerMove.getFromPosition().getRank().ordinal() - lastPlayerMove.getToPosition().getRank().ordinal()) > 1;

        return lastMoveMadeByPawn && lastMoveWasDoubleStep;
    }
}
