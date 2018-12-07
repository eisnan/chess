package app.domain;

import app.domain.util.Tuple;

import java.util.ArrayList;
import java.util.List;

public class StartPositionResolver {

    public List<Tuple<File, Rank>> getPawnsPosition(PiecesColor piecesColor) {
        List<Tuple<File, Rank>> pawnPosition = new ArrayList<>();

        if (piecesColor == PiecesColor.WHITE) {
            for (File file : File.values()) {
                pawnPosition.add(new Tuple<>(file, Rank._2));
            }
        } else if (piecesColor == PiecesColor.BLACK) {
            for (File file : File.values()) {
                pawnPosition.add(new Tuple<>(file, Rank._7));
            }
        }

        return pawnPosition;
    }

    public Tuple<Tuple<File,Rank>, Tuple<File, Rank>> getKingsPositions() {
        return new Tuple<>(new Tuple<>(File.e, Rank._1), new Tuple<>(File.e, Rank._8));
    }
}
