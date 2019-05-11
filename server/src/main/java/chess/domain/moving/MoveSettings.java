package chess.domain.moving;

import chess.domain.Piece;
import chess.domain.Position;
import chess.domain.moving.moves.IterableMove;
import chess.domain.moving.moves.Move;
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
