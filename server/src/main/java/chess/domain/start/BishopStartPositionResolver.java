package chess.domain.start;

import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.*;

public class BishopStartPositionResolver {


    private static final Rank WHITE_BISHOP_RANK = Rank._1;
    private static final Rank BLACK_BISHOP_RANK = Rank._8;

    public Map<PieceColor, Set<Position>> getBishopsPosition() {
        // todo use jdk 9
        Map<PieceColor, Set<Position>> bishopsPosition = new EnumMap<>(PieceColor.class);

        bishopsPosition.put(PieceColor.WHITE, getWhiteBishopsPosition());
        bishopsPosition.put(PieceColor.BLACK, getBlackBishopsPosition());

        return bishopsPosition;
    }

    public Set<Position> getWhiteBishopsPosition() {
        return Set.of(Position.ofValid(File.c, WHITE_BISHOP_RANK), Position.ofValid(File.f, WHITE_BISHOP_RANK));
    }

    public Set<Position> getBlackBishopsPosition() {
        return Set.of(Position.ofValid(File.c, BLACK_BISHOP_RANK), Position.ofValid(File.f, BLACK_BISHOP_RANK));
    }
}
