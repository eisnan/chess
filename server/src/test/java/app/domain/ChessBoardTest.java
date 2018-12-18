package app.domain;

import app.domain.moving.Mover;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoardTest {

    @Test
    public void checkChessBoardModel() {
        ChessBoard chessBoard =  new ChessBoard();

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

        List<Position> availablePositions = new PositionResolver().getAvailablePositions(chessBoard, piece, positionPieceEntry.getKey());

        model.put(positionPieceEntry.getKey(), null);

        new Mover().move(chessBoard, piece, null, availablePositions.get(1));

        Piece[][] arrayModel = chessBoard.getArrayModel();

        ConsolePrinter.print(arrayModel);

    }
}