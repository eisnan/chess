package app.domain;

import app.domain.moving.PlayerMove;
import app.domain.start.HardCodedPositionResolver;
import app.domain.start.StartPositionResolver;
import app.domain.util.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class ChessBoard {

    private static final Predicate<Map.Entry<Position, Piece>> POSITIONS_WITH_NO_PIECES = positionPieceEntry -> positionPieceEntry.getValue() != null;
    private Map<Position, Piece> model = new LinkedHashMap<>();
    private StartPositionResolver startPositionResolver = new HardCodedPositionResolver();
    private LinkedList<PlayerMove> playerMoves = new LinkedList<>();

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

    public void addMove(PlayerMove playerMove) {
        playerMoves.add(playerMove);
    }

    public PlayerMove getLastMove() {
        return playerMoves.isEmpty() ? null : playerMoves.getLast();
    }

    private void observePromotionRanks() {
        List<Position> collect = model.keySet().stream().filter(position -> position.getRank() == Rank._1 || position.getRank() == Rank._8).collect(Collectors.toList());
        System.out.println(collect);
    }

    public Tuple<Position, Piece> getKing(PieceColor pieceColor) {

        Optional<Tuple<Position, Piece>> king = this.model.entrySet().stream()
                .filter(POSITIONS_WITH_NO_PIECES)
                .filter(positionPieceEntry -> !positionPieceEntry.getValue().canBeCaptured() && positionPieceEntry.getValue().getPieceColor() == pieceColor)
                .map(positionPieceEntry -> new Tuple<>(positionPieceEntry.getKey(), positionPieceEntry.getValue())).findFirst();

        if (!king.isPresent()) {
            throw new InvalidStateException();
        }

        return king.get();
    }

    public Collection<Tuple<Position, Piece>> getPieces(PieceType pieceType, PieceColor pieceColor) {
        return getModel().entrySet().stream()
                .filter(POSITIONS_WITH_NO_PIECES)
                .filter(positionPieceEntry -> positionPieceEntry.getValue().getPieceType() == pieceType && positionPieceEntry.getValue().getPieceColor() == pieceColor)
                .map(positionPieceEntry -> new Tuple<>(positionPieceEntry.getKey(), positionPieceEntry.getValue())).collect(Collectors.toSet());
    }

    public Collection<Position> getAdjacentPositions(Position position) {
        Collection<Position> positions = new LinkedHashSet<>();
        int i = position.getFile().ordinal();
        int j = position.getRank().ordinal();

        for (int f = i - 1; f <= i + 1; f++) {
            for (int r = j - 1; r <= j + 1; r++) {
                if (i == f && j == r) {
                    continue;
                }
                try {
                    positions.add(new Position(f, r));
                } catch (InvalidPositionException ex) {
                    log.info(ex.toString());
                }
            }
        }

        return positions;
    }

    public boolean isEmpty(Position position) {
        return this.getModel().get(position) == null;
    }

    public boolean isNotEmpty(Position position) {
        return !isEmpty(position);
    }
}
