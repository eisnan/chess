package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.PieceColor;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Comparator;
import java.util.Set;

public class PromotionMove implements SpecialMove {

    private Comparator<PlayerMove> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<PlayerMove> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? whitePositionComparator : blackPositionComparator;
    }

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {

        PieceColor pieceColor = moveSettings.getPiece().getPieceColor();
        switch (pieceColor) {
            case WHITE:
//                moveSettings.getPiece().getPieceColor().isPromotionRank(moveSettings.)
                break;
            case BLACK:
                break;
        }

        return null;
    }
}
