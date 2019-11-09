package chess.domain;

import chess.domain.util.Pair;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Position {

    private File file;
    private Rank rank;
    private SquareColor squareColor;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
        this.squareColor = determineColor(file, rank);
    }

    public Position(Pair<File, Rank> coordinate) {
        this.file = coordinate.getLeft();
        this.rank = coordinate.getRight();
        this.squareColor = determineColor(file, rank);
    }

    public Position(int fileOrdinal, int rankOrdinal) {
        try {
            this.file = File.values()[fileOrdinal];
            this.rank = Rank.values()[rankOrdinal];
            this.squareColor = determineColor(file, rank);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidPositionException("", fileOrdinal, rankOrdinal);
        }
    }

    @Deprecated
    public Position(char file, int rank) {
        this.file = File.valueOf(String.valueOf(file));
        this.rank = Rank.getRank(String.valueOf(rank));
    }

    public Position(String algebraicNotation) {
        this(File.valueOf(algebraicNotation.substring(0, 1)), Rank.getRank(algebraicNotation.substring(1, 2)));
    }

    public static Position of(String algNotation) {
        return PositionCache.getAlgCache().get(algNotation);
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

    private static class PositionCache {
        static Map<String, Position> algCache = new HashMap<>();
        static Map<File, Map<Rank, Position>> frCache = new HashMap<>();

        static {
            for (File f : File.values()) {
                Map<Rank, Position> ranks = new HashMap<>();
                for (Rank r: Rank.values()) {
                    Position pos = new Position(f, r);
                    ranks.put(r, pos);
                    algCache.put(f.name()+r.getCoordinate(), pos);
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
