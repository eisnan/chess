package app.domain;

import app.domain.util.Tuple;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

//    private Position[][] model = new Position[8][8];

    private Map<Position, Piece> model = new LinkedHashMap<>();
    private StartPositionResolver startPositionResolver = new StartPositionResolver();

//    public Position[][] initModel() {
//
//        for (File file : File.values()) {
//            for (Rank rank : Rank.values()) {
//                model[file.ordinal()][rank.ordinal()] = new Position(new Tuple<>(file, rank));
//            }
//        }
//
//        return model;
//    }


    public Map<Position, Piece> getModel() {
        return model;
    }

    public void initModel() {

        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                model.put(new Position(file, rank), null);
            }
        }

    }

    public void arrangePiecesForStart() {
        List<Tuple<File, Rank>> pawnsPositionWhite = startPositionResolver.getPawnsPosition(PiecesColor.WHITE);
        List<Tuple<File, Rank>> pawnsPositionBlack = startPositionResolver.getPawnsPosition(PiecesColor.BLACK);

        updateModel(pawnsPositionWhite, new Piece(PiecesColor.WHITE, PieceType.PAWN));
        updateModel(pawnsPositionBlack, new Piece(PiecesColor.BLACK, PieceType.PAWN));

        Tuple<Tuple<File, Rank>, Tuple<File, Rank>> kingsPositions = startPositionResolver.getKingsPositions();
        updateModel(kingsPositions, PieceType.KING);
        updateModel(kingsPositions, PieceType.KING);

        getModel();
    }

    private void updateModel(Tuple<Tuple<File, Rank>, Tuple<File, Rank>> positions, PieceType pieceType) {
        model.put(new Position(positions.getLeft()), new Piece(PiecesColor.WHITE, pieceType));
        model.put(new Position(positions.getRight()), new Piece(PiecesColor.BLACK, pieceType));
    }

    private void updateModel(List<Tuple<File, Rank>> positions, Piece piece) {
        positions.forEach(position -> model.putIfAbsent(new Position(position), piece));
    }


}
