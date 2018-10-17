package app.domain;

public enum Rank {
    _1("1"), _2("2"), _3("3"), _4("4"), _5("5"), _6("6"), _7("7"), _8("8");
    private String coordinate;

    Rank(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public static Rank getEnum(String value) {
        value = "_" + value;
        return Rank.valueOf(value);
    }

    @Override
    public String toString() {
        return this.getCoordinate();
    }
}
