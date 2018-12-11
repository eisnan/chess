package app.domain;

public enum PieceColor {

    WHITE, BLACK;

    @Override
    public String toString() {
        return this.name().substring(0,1);
    }
}
