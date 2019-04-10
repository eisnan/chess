package app.domain.moving;

import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.moves.IterableMove;
import app.domain.moving.moves.Move;
import app.domain.moving.rules.MovingRule;
import lombok.Getter;

import java.util.HashMap;
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

    public static MoveSettings getMaxSettings(Position currentPosition, Piece piece, IterableMove iterableMove) {
        Map<Move, Integer> maxSettings = new HashMap<>();
        maxSettings.put(iterableMove, 8);
        return new MoveSettings(currentPosition, piece, maxSettings);
    }
}
