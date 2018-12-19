package app.domain;

import app.domain.moving.Move2;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Position, Piece> model = new LinkedHashMap<>();
    private StartPositionResolver startPositionResolver = new HardCodedPositionResolver();
    private LinkedList<Move2> moves = new LinkedList<>();

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

    public void addMove(Move2 move) {
        moves.add(move);
    }

    public Move2 getLastMove() {
        return moves.isEmpty() ? null : moves.getLast();
    }

    private void observePromotionRanks() {
        List<Position> collect = model.keySet().stream().filter(position -> position.getRank() == Rank._1 || position.getRank() == Rank._8).collect(Collectors.toList());
        System.out.println(collect);
    }
}
