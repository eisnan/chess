package app.domain;

import lombok.Getter;

@Getter
public class Move2 {

    private Piece piece;
    private Position fromPosition;
    private Position toPosition;

    public Move2(Piece piece, Position fromPosition, Position toPosition) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }
}
