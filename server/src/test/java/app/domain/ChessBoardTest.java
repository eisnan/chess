package app.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest {

    @Test
    public void checkChessBoardModel() {
        ChessBoard subject = new ChessBoard();

        subject.initModel();

        System.out.println(subject.getModel());

        subject.arrangePiecesForStart();
    }
}