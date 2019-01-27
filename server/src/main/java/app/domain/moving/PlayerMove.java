package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlayerMove {

    private Piece piece;
    private Position fromPosition;
    private Position toPosition;

}
