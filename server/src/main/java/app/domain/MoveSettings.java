package app.domain;

import lombok.Getter;

import java.util.Map;

@Getter
public class MoveSettings {

    private Position currentPosition;
    private Piece piece;
    private MovingRule movingRule;
    private Map<MoveType, Integer> maxLimit;

    public MoveSettings(Position currentPosition, Piece piece, MovingRule movingRule, Map<MoveType, Integer> maxLimit) {
        this.currentPosition = currentPosition;
        this.piece = piece;
        this.movingRule = movingRule;
        this.maxLimit = maxLimit;
    }

}
