package chess.domain;

import chess.domain.util.Pair;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class Position {

    private File file;
    private Rank rank;
    private SquareColor squareColor;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
        this.squareColor = determineColor(file, rank);
    }

    @Deprecated
    public Position(char file, int rank) {
        this.file = File.valueOf(String.valueOf(file));
        this.rank = Rank.getRank(String.valueOf(rank));
    }

    // static factory methods
    public static Optional<Position> of(int fileOrdinal, int rankOrdinal) {
        try {
            return Optional.of(PositionCache.getFrCache().get(File.values()[fileOrdinal]).get(Rank.values()[rankOrdinal]));
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }
    }

    public static Optional<Position> of(File file, Rank rank) {
        Position position = PositionCache.getFrCache().get(file).get(rank);
        return position != null ? Optional.of(position) : Optional.empty();
    }

    /**
     * Use this just when hardcoding asking for an instance.
     * Will return null if invalid file/rank are used
     */
    public static Position ofValid(File file, Rank rank) {
        return PositionCache.getFrCache().get(file).get(rank);
    }

    public Position(String algebraicNotation) {
        this(File.valueOf(algebraicNotation.substring(0, 1)), Rank.getRank(algebraicNotation.substring(1, 2)));
    }

    public static Position of(String algNotation) {
        return PositionCache.getAlgCache().get(algNotation);
    }

    public static Collection<Position> of(String... algNotations) {
        return Stream.of(algNotations).map(algNotation -> PositionCache.getAlgCache().get(algNotation)).collect(Collectors.toList());
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    public String getAlgebraicNotation() {
        return file.name() + rank.getCoordinate();
    }

    private SquareColor determineColor(File file, Rank rank) {
        return BoardColorCode.get(file.ordinal(), rank.ordinal());
    }

    public String getNotation() {
        if (file == null || rank == null) {
            return "";
        }
        return file.name() + rank.getNotation();
    }

    private static class PositionCache {
        private static Map<String, Position> algCache = new HashMap<>();
        private static Map<File, Map<Rank, Position>> frCache = new EnumMap<>(File.class);

        static {
            for (File f : File.values()) {
                Map<Rank, Position> ranks = new EnumMap<>(Rank.class);
                for (Rank r : Rank.values()) {
                    Position pos = new Position(f, r);
                    ranks.put(r, pos);
                    algCache.put(f.name() + r.getCoordinate(), pos);
                }
                frCache.put(f, ranks);
            }
        }

        static Map<String, Position> getAlgCache() {
            return Collections.unmodifiableMap(algCache);
        }

        static Map<File, Map<Rank, Position>> getFrCache() {
            return Collections.unmodifiableMap(frCache);
        }
    }
}
