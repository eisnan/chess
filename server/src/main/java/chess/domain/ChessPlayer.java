package chess.domain;

import lombok.Getter;

@Getter
public class ChessPlayer {
    private final String playerName;

    public ChessPlayer(String playerName) {
        this.playerName = playerName;
    }
}
