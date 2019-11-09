package chess.domain.start;

import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.*;

public class KnightStartPositionResolver {


    private Rank WHITE_KNIGHT_RANK = Rank._1;
    private Rank BLACK_KNIGHT_RANK = Rank._8;

    public Map<PieceColor, Set<Position>> getKnightsPosition() {
        // todo use jdk 9
        Map<PieceColor, Set<Position>> knightsPosition = new HashMap<>();

        knightsPosition.put(PieceColor.WHITE, getWhiteKnightsPosition());
        knightsPosition.put(PieceColor.BLACK, getBlackKnightsPosition());

        return knightsPosition;
    }

    public Set<Position> getWhiteKnightsPosition() {
        return new HashSet<>(Arrays.asList(Position.ofValid(File.b, WHITE_KNIGHT_RANK), Position.ofValid(File.g, WHITE_KNIGHT_RANK)));
    }

    public Set<Position> getBlackKnightsPosition() {
        return new HashSet<>(Arrays.asList(Position.ofValid(File.b, BLACK_KNIGHT_RANK), Position.ofValid(File.g, BLACK_KNIGHT_RANK)));
    }
}
