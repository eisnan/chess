package app.domain;

import app.domain.moving.PlayerMove;
import app.domain.moving.PlayerAction;
import app.domain.util.Tuple;
import app.domain.util.Util;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChessBoardTest {

    private ChessBoard chessBoard = new ChessBoard();

    @Before
    public void setUp() {
        chessBoard.initModel();
        chessBoard.arrangePiecesForStart();
    }


    @Test
    public void testChessGame() {

        PlayerAction playerAction = new PlayerAction();
        playerAction.move(chessBoard, new PlayerMove(new Piece("wP"), new Position("d2"), new Position("d4")));
        playerAction.move(chessBoard, new PlayerMove(new Piece("bP"), new Position("e7"), new Position("e6")));
        playerAction.move(chessBoard, new PlayerMove(new Piece("wN"), new Position("b1"), new Position("d2")));
        playerAction.move(chessBoard, new PlayerMove(new Piece("bB"), new Position("f8"), new Position("b4")));

        Collection<Position> availablePositions = new PositionResolver().getAvailablePositions(chessBoard, new Position("d2"));

        System.out.println(availablePositions);


        ConsolePrinter.print(chessBoard.getArrayModel());

    }

    @Test
    public void checkChessBoardModel() {
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.initModel();

        chessBoard.getArrayModel();

        Map<Position, Piece> model = chessBoard.getModel();
        System.out.println(model);

        chessBoard.arrangePiecesForStart();

        chessBoard.getArrayModel();

//        System.out.println(chessBoard.getModel().entrySet().stream().filter(positionPieceEntry -> positionPieceEntry.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        Optional<Map.Entry<Position, Piece>> first = model.entrySet().stream().filter(positionPieceEntry -> positionPieceEntry.getKey().equals(new Position(File.c, Rank._2))).findFirst();
        Map.Entry<Position, Piece> positionPieceEntry = first.get();
        Piece piece = positionPieceEntry.getValue();
        System.out.println(positionPieceEntry);

        List<Position> availablePositions = new ArrayList<>(new PositionResolver().getAvailablePositions(chessBoard, positionPieceEntry.getKey()));

        model.put(positionPieceEntry.getKey(), null);

        new PlayerAction().move(chessBoard, piece, null, availablePositions.get(1));

        Piece[][] arrayModel = chessBoard.getArrayModel();

        ConsolePrinter.print(arrayModel);

    }

    @Test
    public void testGetKing() {
        chessBoard.initModel();
        chessBoard.arrangePiecesForStart();

        Tuple<Position, Piece> whiteKing = chessBoard.getKing(PieceColor.WHITE);
        assertEquals(whiteKing.getLeft(), new Position(File.e, Rank._1));

        Tuple<Position, Piece> blackKing = chessBoard.getKing(PieceColor.BLACK);
        assertEquals(blackKing.getLeft(), new Position(File.e, Rank._8));
    }

    @Test
    public void testGetAdjacentPositionsCorners() {
        Position positionDownLeftCorner = new Position(File.a, Rank._1);
        Collection<Position> adjacentPositionsToDownLeftCorner = chessBoard.getAdjacentPositions(positionDownLeftCorner);
        assertTrue(Util.collectionsEqualIgnoreOrder(adjacentPositionsToDownLeftCorner, Sets.newHashSet(new Position(File.a, Rank._2), new Position(File.b, Rank._1), new Position(File.b, Rank._2))));

        Position positionUpperLeftCorner = new Position(File.a, Rank._8);
        Collection<Position> adjacentPositionsToUpperLeftCorner = chessBoard.getAdjacentPositions(positionUpperLeftCorner);
        assertTrue(Util.collectionsEqualIgnoreOrder(adjacentPositionsToUpperLeftCorner, Sets.newHashSet(new Position(File.a, Rank._7), new Position(File.b, Rank._8), new Position(File.b, Rank._7))));

        Position positionDownRightCorner = new Position(File.h, Rank._1);
        Collection<Position> adjacentPositionsToDownRightCorner = chessBoard.getAdjacentPositions(positionDownRightCorner);
        assertTrue(Util.collectionsEqualIgnoreOrder(adjacentPositionsToDownRightCorner, Sets.newHashSet(new Position(File.h, Rank._2), new Position(File.g, Rank._1), new Position(File.g, Rank._2))));

        Position positionUpperRightCorner = new Position(File.h, Rank._8);
        Collection<Position> adjacentPositionsToUpperRightCorner = chessBoard.getAdjacentPositions(positionUpperRightCorner);
        assertTrue(Util.collectionsEqualIgnoreOrder(adjacentPositionsToUpperRightCorner, Sets.newHashSet(new Position(File.h, Rank._7), new Position(File.g, Rank._7), new Position(File.g, Rank._8))));
    }

    @Test
    public void testGetAdjacentPositionsMiddleOfTable() {

        Collection<Position> adjacentPositions = chessBoard.getAdjacentPositions(new Position(File.e, Rank._4));
        System.out.println(adjacentPositions);

    }

    @Test
    public void testGetAdjacentPositionsEdgeOfTable() {

        Collection<Position> adjacentPositions = chessBoard.getAdjacentPositions(new Position(File.d, Rank._1));
        System.out.println(adjacentPositions);

    }
}