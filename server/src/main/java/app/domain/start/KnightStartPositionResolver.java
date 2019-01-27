package app.domain.start;

import app.domain.File;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.Rank;

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
        return new HashSet<>(Arrays.asList(new Position(File.b, WHITE_KNIGHT_RANK), new Position(File.g, WHITE_KNIGHT_RANK)));
    }

    public Set<Position> getBlackKnightsPosition() {
        return new HashSet<>(Arrays.asList(new Position(File.b, BLACK_KNIGHT_RANK), new Position(File.g, BLACK_KNIGHT_RANK)));
    }
}
