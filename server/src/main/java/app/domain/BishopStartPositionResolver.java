package app.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BishopStartPositionResolver {


    private Rank WHITE_BISHOP_RANK = Rank._1;
    private Rank BLACK_BISHOP_RANK = Rank._8;

    public Map<PieceColor, Set<Position>> getBishopsPosition() {
        // todo use jdk 9
        Map<PieceColor, Set<Position>> bishopsPosition = new HashMap<>();

        bishopsPosition.put(PieceColor.WHITE, getWhiteBishopsPosition());
        bishopsPosition.put(PieceColor.BLACK, getBlackBishopsPosition());

        return bishopsPosition;
    }

    public Set<Position> getWhiteBishopsPosition() {
        return new HashSet<>(Arrays.asList(new Position(File.c, WHITE_BISHOP_RANK), new Position(File.f, WHITE_BISHOP_RANK)));
    }

    public Set<Position> getBlackBishopsPosition() {
        return new HashSet<>(Arrays.asList(new Position(File.c, BLACK_BISHOP_RANK), new Position(File.f, BLACK_BISHOP_RANK)));
    }
}
