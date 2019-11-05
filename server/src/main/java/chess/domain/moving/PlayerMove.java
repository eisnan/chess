package chess.domain.moving;

import chess.domain.Piece;
import chess.domain.Position;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerMove {

    private Piece piece;
    private Position fromPosition;
    private Position toPosition;
    private MoveType moveType;

    public PlayerMove(Piece piece, Position fromPosition, Position toPosition) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public PlayerMove(PlayerMove playerMove, MoveType moveType) {
        this.piece = playerMove.piece;
        this.fromPosition = playerMove.fromPosition;
        this.toPosition = playerMove.toPosition;
        this.moveType = moveType;
    }

}
