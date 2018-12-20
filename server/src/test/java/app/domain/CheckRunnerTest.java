package app.domain;

import org.junit.Before;
import org.junit.Test;

public class CheckRunnerTest {

   private ChessBoard chessBoard = new ChessBoard();


    @Before
    public void setUp() {
        chessBoard.initModel();
        chessBoard.arrangePiecesForStart();

    }

    @Test
    public void check() {
        CheckRunner checkRunner = new CheckRunner();

        checkRunner.isKingInCheck(chessBoard, PieceColor.WHITE);


    }
}