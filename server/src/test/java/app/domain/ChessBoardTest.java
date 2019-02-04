package app.domain;

import app.domain.moving.PlayerAction;
import app.domain.moving.PlayerMove;
import app.domain.util.Tuple;
import app.domain.util.Util;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChessBoardTest {

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
    }


    @Test
    public void testChessGame() {

        PlayerAction playerAction = new PlayerAction();
        playerAction.move(chessBoard, new PlayerMove(new Piece("wP"), new Position("d2"), new Position("d4")));
//        playerAction.move(chessBoard, new PlayerMove(new Piece("bP"), new Position("e7"), new Position("e6")));
//        playerAction.move(chessBoard, new PlayerMove(new Piece("wN"), new Position("b1"), new Position("d2")));
//        playerAction.move(chessBoard, new PlayerMove(new Piece("bB"), new Position("f8"), new Position("b4")));

        Collection<PlayerMove> availablePositions = new PositionResolver().getAvailablePositions(chessBoard, new Position("d4"));

        System.out.println(availablePositions);


        ConsolePrinter.print(chessBoard.getArrayModel());

    }
//+
}