package chess.domain.moving;

import chess.domain.Piece;
import chess.domain.Position;
import lombok.Getter;

import java.util.Objects;

@Getter
public class PlayerMove {

    private Piece piece;
    private Position fromPosition;
    private Position toPosition;
    private MoveType moveType;

    public static PlayerMove of(Piece piece, Position fromPosition, Position toPosition) {
        return new PlayerMove(piece, fromPosition, toPosition);
    }

    public static PlayerMove of(Piece piece, Position fromPosition, Position toPosition, MoveType moveType) {
        return new PlayerMove(piece, fromPosition, toPosition, moveType);
    }

    public static PlayerMove of(PlayerMove playerMove, MoveType moveType) {
        return new PlayerMove(playerMove, moveType);
    }

    private PlayerMove(Piece piece, Position fromPosition, Position toPosition) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    private PlayerMove(PlayerMove playerMove, MoveType moveType) {
        this.piece = playerMove.piece;
        this.fromPosition = playerMove.fromPosition;
        this.toPosition = playerMove.toPosition;
        this.moveType = moveType;
    }

    private PlayerMove(Piece piece, Position fromPosition, Position toPosition, MoveType moveType) {
        this.piece = piece;
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
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
        PlayerMove that = (PlayerMove) o;
        return Objects.equals(piece, that.piece) &&
                Objects.equals(fromPosition, that.fromPosition) &&
                Objects.equals(toPosition, that.toPosition) &&
                moveType == that.moveType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, fromPosition, toPosition, moveType);
    }

    @Override
    public String toString() {
        return "PlayerMove{" +
                "piece=" + piece +
                ", fromPosition=" + fromPosition +
                ", toPosition=" + toPosition +
                ", moveType=" + moveType +
                '}';
    }
}
