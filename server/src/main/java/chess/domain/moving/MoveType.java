package chess.domain.moving;

import java.util.stream.Stream;

/**
 * Created by Gabs on 2/1/2019.
 */
public enum MoveType {
    MOVE, CAPTURE, CHECK, QUEEN_SIDE_CASTLING, KING_SIDE_CASTLING, PROMOTION, EN_PASSANT;

    public static boolean isCastling(MoveType moveType) {
        return Stream.of(MoveType.KING_SIDE_CASTLING, MoveType.QUEEN_SIDE_CASTLING).anyMatch(mv -> mv == moveType);
    }


}
