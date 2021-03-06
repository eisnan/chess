package chess.domain.start;

import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.*;

public class RookStartPositionResolver {


    private Rank WHITE_ROOK_RANK = Rank._1;
    private Rank BLACK_ROOK_RANK = Rank._8;

    public Map<PieceColor, Set<Position>> getRooksPosition() {
        // todo use jdk 9
        Map<PieceColor, Set<Position>> rooksPosition = new HashMap<>();

        rooksPosition.put(PieceColor.WHITE, getWhiteRooksPosition());
        rooksPosition.put(PieceColor.BLACK, getBlackRooksPosition());

        return rooksPosition;
    }

    public Set<Position> getWhiteRooksPosition() {
        return new HashSet<>(Arrays.asList(Position.ofValid(File.a, WHITE_ROOK_RANK), Position.ofValid(File.h, WHITE_ROOK_RANK)));
    }

    public Set<Position> getBlackRooksPosition() {
        return new HashSet<>(Arrays.asList( Position.ofValid(File.a, BLACK_ROOK_RANK),  Position.ofValid(File.h, BLACK_ROOK_RANK)));
    }
}
