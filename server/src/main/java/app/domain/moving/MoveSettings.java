package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.rules.MovingRule;
import lombok.Getter;

import java.util.Map;

@Getter
public class MoveSettings {

    private Position currentPosition;
    private Piece piece;
    private MovingRule movingRule;
    private Map<MoveDescriber, Integer> movingSettings;

    public MoveSettings(Position currentPosition, Piece piece, MovingRule movingRule, Map<MoveDescriber, Integer> movingSettings) {
        this.currentPosition = currentPosition;
        this.piece = piece;
        this.movingRule = movingRule;
        this.movingSettings = movingSettings;
    }
}
