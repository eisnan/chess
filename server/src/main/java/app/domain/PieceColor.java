package app.domain;

public enum PieceColor {

    WHITE, BLACK;


    public String getColorNotation() {
        return this.name().substring(0,1).toLowerCase();
    }
}
