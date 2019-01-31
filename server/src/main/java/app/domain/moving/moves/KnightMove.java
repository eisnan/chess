package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.InvalidPositionException;
import app.domain.Position;
import app.domain.moving.MoveSettings;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Slf4j
public class KnightMove implements Move {

    @Override
    public Set<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        Set<Position> aboveMoves = aboveMoves(moveSettings.getCurrentPosition());
        aboveMoves.addAll(belowMoves(moveSettings.getCurrentPosition()));

        return aboveMoves;
    }

    private Set<Position> belowMoves(Position currentPosition) {
        Set<Position> belowPositions = new HashSet<>();
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

    private Set<Position> aboveMoves(Position currentPosition) {
        Set<Position> abovePositions = new HashSet<>();
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
