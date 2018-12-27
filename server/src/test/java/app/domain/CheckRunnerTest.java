package app.domain;

import app.domain.moving.PlayerAction;
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

        new PlayerAction().move(chessBoard, new Piece(PieceColor.WHITE, PieceType.PAWN), new Position(File.e, Rank._2), new Position(File.e, Rank._4));

        checkRunner.isKingInCheck(chessBoard, PieceColor.WHITE);


    }
}