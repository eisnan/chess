package chess.domain;

import org.junit.Test;

public class ChessGameTest {

    @Test
    public void startGame() {

        GameId gameId = new GameId();
        ChessBoard chessBoard = new ChessBoard();
        ChessPlayer whitePlayer = new ChessPlayer("Gabs");
        ChessPlayer blackPlayer = new ChessPlayer("Horia");
        ChessGame chessGame = new ChessGame(gameId, whitePlayer, blackPlayer);

        chessGame.startGame();


    }
}