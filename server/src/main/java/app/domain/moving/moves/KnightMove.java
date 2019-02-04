package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.InvalidPositionException;
import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerMove;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class KnightMove implements Move {

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        Set<PlayerMove> aboveMoves = aboveMoves(moveSettings.getCurrentPosition(), moveSettings.getPiece());
        aboveMoves.addAll(belowMoves(moveSettings.getCurrentPosition(), moveSettings.getPiece()));

        return aboveMoves;
    }

    private Set<PlayerMove> belowMoves(Position currentPosition, Piece piece) {
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

        return belowPositions.stream()
                .map(position -> new PlayerMove(piece, currentPosition, position))
                .collect(Collectors.toSet());
    }

    private Set<PlayerMove> aboveMoves(Position currentPosition, Piece piece) {
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

        return abovePositions.stream()
                .map(position -> new PlayerMove(piece, currentPosition, position))
                .collect(Collectors.toSet());
    }
}
