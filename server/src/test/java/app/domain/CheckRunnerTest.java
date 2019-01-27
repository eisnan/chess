package app.domain;

import app.domain.moving.PlayerAction;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CheckRunnerTest {

   private ChessBoard chessBoard = new ChessBoard();


    @Before
    public void setUp() {

    }

    @Test
    public void blackDirectlyChecksWhite() {
        CheckRunner checkRunner = new CheckRunner();
        PlayerAction playerAction = new PlayerAction();

        playerAction.move(chessBoard, new Piece(PieceColor.WHITE, PieceType.PAWN), new Position(File.d, Rank._2), new Position(File.d, Rank._4));
        playerAction.move(chessBoard, new Piece(PieceColor.BLACK, PieceType.PAWN), new Position(File.e, Rank._7), new Position(File.e, Rank._6));
        playerAction.move(chessBoard, new Piece(PieceColor.WHITE, PieceType.PAWN), new Position(File.g, Rank._1), new Position(File.f, Rank._3));
        playerAction.move(chessBoard, new Piece(PieceColor.BLACK, PieceType.BISHOP), new Position(File.f, Rank._8), new Position(File.b, Rank._4));

        ConsolePrinter.print(chessBoard.getArrayModel());

        boolean kingInCheck = checkRunner.isKingInCheck(chessBoard, PieceColor.WHITE);

        assertTrue(kingInCheck);
    }


}