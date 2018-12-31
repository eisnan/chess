package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.InvalidPositionException;
import app.domain.Position;
import app.domain.moving.AscendingPositionComparator;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

// TODO REFACTOR
@Slf4j
public class KnightMove implements MoveDescriber {

    private Comparator<Position> positionComparator = new AscendingPositionComparator();


    @Override
    public Collection<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        List<Position> aboveMoves = aboveMoves(moveSettings.getCurrentPosition());
        aboveMoves.addAll(belowMoves(moveSettings.getCurrentPosition()));

        return aboveMoves;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> fileFunction() {
        return null;
    }

    @Override
    public BiFunction<Integer, Integer, Integer> rankFunction() {
        return null;
    }

    @Override
    public Comparator<Position> getPositionComparator() {
        return positionComparator;
    }

    private List<Position> belowMoves(Position currentPosition) {
        List<Position> belowPositions = new ArrayList<>();
        int fileOrdinal = currentPosition.getFile().ordinal();
        int rankOrdinal = currentPosition.getRank().ordinal();
        // todo refactor this
        try {
            belowPositions.add(new Position(fileOrdinal - 2, rankOrdinal - 1));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }
        try {
            belowPositions.add(new Position(fileOrdinal - 1, rankOrdinal - 2));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }
        try {
            belowPositions.add(new Position(fileOrdinal + 2, rankOrdinal - 1));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }
        try {
            belowPositions.add(new Position(fileOrdinal + 1, rankOrdinal - 2));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }

        return belowPositions;
    }

    private List<Position> aboveMoves(Position currentPosition) {
        List<Position> abovePositions = new ArrayList<>();
        int fileOrdinal = currentPosition.getFile().ordinal();
        int rankOrdinal = currentPosition.getRank().ordinal();
        // todo refactor this
        try {
            abovePositions.add(new Position(fileOrdinal - 2, rankOrdinal + 1));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }
        try {
            abovePositions.add(new Position(fileOrdinal - 1, rankOrdinal + 2));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }
        try {
            abovePositions.add(new Position(fileOrdinal + 2, rankOrdinal + 1));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }
        try {
            abovePositions.add(new Position(fileOrdinal + 1, rankOrdinal + 2));
        } catch (InvalidPositionException ex) {
            log.info(ex.toString());
        }

        return abovePositions;
    }
}
