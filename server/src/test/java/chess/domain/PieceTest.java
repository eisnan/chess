package chess.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void testPieceCache() {

        Piece piece1 = Piece.of(PieceColor.WHITE, PieceType.PAWN);
        Piece piece2 = Piece.of(PieceColor.WHITE, PieceType.PAWN);

        assertNotNull(piece1);
        assertNotNull(piece2);
        assertEquals(piece1, piece2);
    }

}