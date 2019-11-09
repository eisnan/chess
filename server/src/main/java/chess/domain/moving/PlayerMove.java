package chess.domain.moving;

import chess.domain.Piece;
import chess.domain.Position;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
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

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerMove pMove = (PlayerMove) o;
        return piece.equals(pMove.piece) &&
                fromPosition.equals(pMove.fromPosition) &&
                toPosition.equals(pMove.toPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, fromPosition, toPosition);
    }
}
