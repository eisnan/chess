//package app.domain.moving.moves;
//
//import app.domain.*;
//import app.domain.moving.MoveSettings;
//import app.domain.util.Util;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.SortedSet;
//
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by Gabs on 1/31/2019.
// */
//public class ForwardMoveTest {
//
//    private ForwardMove subject = new ForwardMove();
//
//    @Test
//    public void checkMove() throws Exception {
//
//        ChessBoard chessBoard = new ChessBoard();
//        Map<Move, Integer> settings = new HashMap<>();
//        settings.put(subject, 2);
//        MoveSettings moveSettings = new MoveSettings(new Position("d2"), new Piece(PieceColor.WHITE, PieceType.PAWN), settings);
//
//
//        SortedSet<Position> positions = subject.checkMove(chessBoard, moveSettings);
//
//        assertTrue(Util.collectionsEqualIgnoreOrder(Arrays.asList(new Position("d3"), new Position("d4")), positions));
//    }
//}