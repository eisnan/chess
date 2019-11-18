package chess.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ChessGame {

    private final GameId gameId;
    private final ChessPlayer whitePlayer;
    private final ChessPlayer blackPlayer;
    private ChessEngine engine;

    public ChessGame(GameId gameId, ChessPlayer whitePlayer, ChessPlayer blackPlayer) {
        this.gameId = gameId;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    public ChessEngine startGame() {
      log.info("Starting game:" + gameId.getId());

      ChessBoard chessBoard = new ChessBoard();
      engine = new ChessEngine(chessBoard);

      log.info("Game started");

      return engine;
    }

    public ChessPlayer getPlayerByColor(PieceColor pieceColor) {
        return pieceColor == PieceColor.WHITE ? whitePlayer : blackPlayer;
    }





}
