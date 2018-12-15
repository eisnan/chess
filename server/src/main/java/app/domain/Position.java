package app.domain;

import app.domain.util.Tuple;

import java.util.Objects;

public class Position {

    private File file;
    private Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(Tuple<File, Rank> coordinate) {
        this.file = coordinate.getLeft();
        this.rank = coordinate.getRight();
    }

    public Position(int fileOrdinal, int rankOrdinal) {

        if (fileOrdinal > 7 && rankOrdinal > 7) {

        }
        try {
            this.file = File.values()[fileOrdinal];
            this.rank = Rank.values()[rankOrdinal];
        } catch (IndexOutOfBoundsException ex) {
            throw  new InvalidPositionException("");
        }
    }

    public Position(char file, int rank) {
        this.file = File.valueOf(String.valueOf(file));
        this.rank = Rank.getEnum(String.valueOf(rank));
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
}
