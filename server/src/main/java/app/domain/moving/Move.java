package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import lombok.Getter;

@Getter
public class Move {

    private Piece piece;
    private Position fromPosition;
    private Position toPosition;

    public Move(Piece piece, Position fromPosition, Position toPosition) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

}
