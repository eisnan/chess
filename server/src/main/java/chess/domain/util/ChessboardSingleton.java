package chess.domain.util;

import chess.domain.ChessBoard;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public enum ChessboardSingleton {

    INSTANCE;
    private final Map<UUID, ChessBoard> gameMemory = new HashMap<>();
}
