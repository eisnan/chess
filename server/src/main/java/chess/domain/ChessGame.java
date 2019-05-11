package chess.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ChessGame {

    private GameId gameId;
    private ChessBoard chessBoard;
    private ChessPlayer whitePlayer;
    private ChessPlayer blackPlayer;

    public ChessGame(GameId gameId, ChessBoard chessBoard, ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        this.gameId = gameId;
        this.chessBoard = chessBoard;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public void startGame() {
      log.info("Starting game:" + gameId.getId());

      log.info("Game started");
    }
}
