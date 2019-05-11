package chess.domain.start;

import chess.domain.File;
import chess.domain.PieceColor;
import chess.domain.Position;
import chess.domain.Rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PawnStartPositionResolver {

    private Rank BLACK_PAWN_RANK = Rank._7;
    private Rank WHITE_PAWN_RANK = Rank._2;

    public Map<PieceColor, Set<Position>> getPawnsPosition() {
        // todo use jdk 9
        Map<PieceColor, Set<Position>> pawnsPostion = new HashMap<>();

        pawnsPostion.put(PieceColor.WHITE, getWhitePawnsPosition());
        pawnsPostion.put(PieceColor.BLACK, getBlackPawnsPosition());

        return pawnsPostion;
    }

    public Set<Position> getWhitePawnsPosition() {
        return Stream.of(File.values()).
                map(file -> new Position(file, WHITE_PAWN_RANK)).
                collect(Collectors.toSet());
    }

    public Set<Position> getBlackPawnsPosition() {
        return Stream.of(File.values()).
                map(file -> new Position(file, BLACK_PAWN_RANK)).
                collect(Collectors.toSet());
    }
}
