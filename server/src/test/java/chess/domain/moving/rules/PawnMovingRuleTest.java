//package app.domain.moving.rules;
//
//import app.domain.*;
//import PlayerAction;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import static ConsolePrinter.print;
//import static junit.framework.TestCase.assertEquals;
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by Gabs on 1/27/2019.
// */
//public class PawnMovingRuleTest {
//
//    private PawnMovingRule subject = new PawnMovingRule();
//    private ChessBoard chessBoard;
//    private PlayerAction playerAction;
//
//    @Before
//    public void setUp() {
//        chessBoard = new ChessBoard();
//        playerAction = new PlayerAction();
//    }
//
//    @Test
//    public void testPawnMovements() {
//        print(chessBoard.getArrayModel());
//
//        Position fromPosition = new Position("b2");
//        Piece wbPawn = chessBoard.getModel().get(fromPosition);
//        Collection<Position> movePositions = subject.getMovePositions(chessBoard, wbPawn, fromPosition);
//        assertEquals(2, movePositions.size());
//        Position b3 = new Position("b3");
//        Position b4 = new Position("b4");
//        assertTrue(movePositions.containsAll(Arrays.asList(b3, b4)));
//
//        playerAction.move(chessBoard, wbPawn, fromPosition, b4);
//
//        Piece bbPawn = new Piece(PieceColor.BLACK, PieceType.PAWN);
//
//
//        print(chessBoard.getArrayModel());
//
//    }
//
//}