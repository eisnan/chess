package app.domain;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessBoardTest {

    @Test
    public void checkChessBoardModel() {
        ChessBoard subject =  ChessBoard.INSTANCE;

        subject.initModel();

        Map<Position, Piece> model = subject.getModel();
        System.out.println(model);

        subject.arrangePiecesForStart();

//        System.out.println(subject.getModel().entrySet().stream().filter(positionPieceEntry -> positionPieceEntry.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));

        Optional<Map.Entry<Position, Piece>> first = model.entrySet().stream().filter(positionPieceEntry -> positionPieceEntry.getKey().equals(new Position(File.c, Rank._2))).findFirst();
        Map.Entry<Position, Piece> positionPieceEntry = first.get();
        Piece piece = positionPieceEntry.getValue();
        System.out.println(positionPieceEntry);

        List<Position> availablePositions = new PositionResolver().getAvailablePositions(piece, positionPieceEntry.getKey());

        model.put(positionPieceEntry.getKey(), null);

        new Mover().move(subject, piece, null, availablePositions.get(1));

        Piece[][] arrayModel = subject.getArrayModel();

        ConsolePrinter.print(arrayModel);

    }
}