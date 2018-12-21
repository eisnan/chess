package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class KnightMovingRule implements MovingRule {

    @Override
    public Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {

        List<Position> aboveMoves = aboveMoves(currentPosition);
        aboveMoves.addAll(belowMoves(currentPosition));

        Collection<Position> availableMoves = removeInvalidPositions(chessBoard,  currentPosition, piece, aboveMoves);
        return availableMoves;
    }


    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMaxSquares().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
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

    @Override
    public Collection<Position> removeInvalidPositions(ChessBoard chessBoard, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        return positions.stream()
                .filter(position -> {
                    Piece piece = chessBoard.getModel().get(position);
                    return piece == null || piece.getPieceColor() != selectedPiece.getPieceColor();
                }).collect(Collectors.toCollection(TreeSet::new));
    }
}
