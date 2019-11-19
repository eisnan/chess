package chess.domain.moving.moves;

import chess.domain.*;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.comparators.DescendingPositionComparator;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

/**
 * Created by Gabs on 1/26/2019.
 */
public class KingSideCastling implements SpecialMove {

    private Comparator<PlayerMove> blackPositionComparator = new AscendingPositionComparator();
    private Comparator<PlayerMove> whitePositionComparator = new DescendingPositionComparator();

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        switch (moveSettings.getPiece().getPieceColor()) {
            case WHITE:
                return Collections.singleton(PlayerMove.of(Piece.getWhitePiece(PieceType.KING), Position.of("e1"), Position.of("g1"), MoveType.KING_SIDE_CASTLING));
            case BLACK:
                return Collections.singleton(PlayerMove.of(Piece.getBlackPiece(PieceType.KING), Position.of("e8"), Position.of("g8"), MoveType.KING_SIDE_CASTLING));
        }

        return Collections.emptySet();
    }

    @Override
    public Comparator<PlayerMove> getPositionComparator(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? blackPositionComparator : whitePositionComparator;
    }
}
