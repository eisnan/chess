package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import lombok.Getter;

@Getter
public class Move2 {

    private Piece piece;
    private Position fromPosition;
    private Position toPosition;
    private boolean captureEnPassant;

    public Move2(Piece piece, Position fromPosition, Position toPosition) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public Move2(Piece piece, Position fromPosition, Position toPosition, boolean captureEnPassant) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        this.captureEnPassant = captureEnPassant;
    }
}
