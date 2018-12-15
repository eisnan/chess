package app.domain;

import app.domain.util.Tuple;

import java.util.*;

public enum ChessBoard {

    INSTANCE;
//    private Position[][] model = new Position[8][8];

    private Map<Position, Piece> model = new LinkedHashMap<>();
    private StartPositionResolver startPositionResolver = new HardCodedPositionResolver();
    private LinkedList<Move2> moves = new LinkedList<>();

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

    /**
     * a 1-8
     * b 1-8
     *
     * @return
     */
    public Piece[][] getArrayModel() {

        Piece[][] arrayModel = new Piece[8][8];
        for (int j = Rank.values().length - 1; j >= 0; j--) {
            for (int i = 0; i < File.values().length; i++) {
                arrayModel[i][j] = model.get(new Position(File.values()[i], Rank.values()[j]));
            }
        }

        return arrayModel;
    }

    public void initModel() {

        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                model.put(new Position(file, rank), null);
            }
        }

    }

    public void arrangePiecesForStart() {

        Map<Piece, Set<Position>> initialPositions = startPositionResolver.getInitialPositions();

        updateModel(initialPositions);

    }

    private void updateModel(Map<Piece, Set<Position>> initialPositions) {
        initialPositions.forEach((piece, positions) -> positions.forEach(position -> model.put(position, piece)));
    }

    private void updateModel(Tuple<Tuple<File, Rank>, Tuple<File, Rank>> positions, PieceType pieceType) {
        model.put(new Position(positions.getLeft()), new Piece(PieceColor.WHITE, pieceType));
        model.put(new Position(positions.getRight()), new Piece(PieceColor.BLACK, pieceType));
    }

    private void updateModel(List<Tuple<File, Rank>> positions, Piece piece) {
        positions.forEach(position -> model.putIfAbsent(new Position(position), piece));
    }


    public Position getCurrentPosition(Piece selectedPiece) {
        return model.entrySet().stream().filter(positionPieceEntry -> positionPieceEntry.getValue().equals(selectedPiece))
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }


    public void addMove(Move2 move) {
        moves.add(move);
    }

    public Move2 getLastMove() {
        return moves.isEmpty() ? null : moves.getLast();
    }
}
