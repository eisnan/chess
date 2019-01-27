package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.InvalidPositionException;
import app.domain.Position;
import app.domain.moving.MoveSettings;
import app.domain.moving.SpecialMove;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class KnightMove implements SpecialMove {

    @Override
    public SortedSet<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        SortedSet<Position> aboveMoves = aboveMoves(moveSettings.getCurrentPosition());
        aboveMoves.addAll(belowMoves(moveSettings.getCurrentPosition()));

        return aboveMoves;
    }

    private SortedSet<Position> belowMoves(Position currentPosition) {
        SortedSet<Position> belowPositions = new TreeSet<>(getPositionComparator());
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

    private SortedSet<Position> aboveMoves(Position currentPosition) {
        SortedSet<Position> abovePositions = new TreeSet<>(getPositionComparator());
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
