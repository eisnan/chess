package chess.domain;

import java.util.Arrays;
import java.util.LinkedList;

public enum Rank {
    _1("1"), _2("2"), _3("3"), _4("4"), _5("5"), _6("6"), _7("7"), _8("8");
    private String coordinate;
    private static LinkedList<Rank> vals = new LinkedList<>(Arrays.asList(values()));

    Rank(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public static Rank getRank(String value) {
        value = "_" + value;
        return Rank.valueOf(value);
    }


    public Rank next() {
        try {
            return vals.get(this.ordinal() + 1);
        } catch (IndexOutOfBoundsException ex) {
            return vals.getLast();
        }
    }


    public Rank previous() {
        try {
            return vals.get(this.ordinal() - 1);
        } catch (IndexOutOfBoundsException ex) {
            return vals.getFirst();
        }
    }


    @Override
    public String toString() {
        return this.getCoordinate();
    }
}
