package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.moves.Move;
import app.domain.moving.rules.MovingRule;
import lombok.Getter;

import java.util.Map;

@Getter
public class MoveSettings {

    private Position currentPosition;
    private Piece piece;
    private Map<Move, Integer> settings;

    public MoveSettings(Position currentPosition, Piece piece, Map<Move, Integer> settings) {
        this.currentPosition = currentPosition;
        this.piece = piece;
        this.settings = settings;
    }
}
