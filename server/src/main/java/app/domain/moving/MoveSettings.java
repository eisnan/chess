package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import lombok.Getter;

import java.util.Map;

@Getter
public class MoveSettings {

    private Position currentPosition;
    private Piece piece;
    private MovingRule movingRule;
    private Map<MoveDescriber, Integer> maxLimit;

    public MoveSettings(Position currentPosition, Piece piece, MovingRule movingRule, Map<MoveDescriber, Integer> maxLimit) {
        this.currentPosition = currentPosition;
        this.piece = piece;
        this.movingRule = movingRule;
        this.maxLimit = maxLimit;
    }
}
