package chess.domain;

import java.util.UUID;

public class GameId {
    private final String uniqueID;

    public GameId() {
        uniqueID = UUID.randomUUID().toString();
    }

    public String getId() {
        return uniqueID;
    }
}
