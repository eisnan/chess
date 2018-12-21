package app.domain.moving.rules;

import app.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class KnightMovingRuleTest {

    private ChessBoard chessBoard = new ChessBoard();

    @Before
    public void setUp() {
        chessBoard.initModel();
        chessBoard.arrangePiecesForStart();
    }

    @Test
    public void getPossiblePositions() {
        KnightMovingRule knightMovingRule = new KnightMovingRule();

        Collection<Position> possiblePositions = knightMovingRule.getAvailablePositions(chessBoard, new Piece(PieceColor.WHITE, PieceType.KNIGHT), new Position(File.b, Rank._1));

        System.out.println(possiblePositions);
    }
}