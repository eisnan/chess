package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveSettings;
import app.domain.moving.moves.Move;
import app.domain.moving.validators.PositionValidator;
import app.domain.util.Tuple;

import java.util.*;

public interface MovingRule {
    Map<PieceColor, Collection<Tuple<Move, Integer>>> getMoveParameters();

    Map<PieceColor, Collection<Tuple<Move, Integer>>> getCaptureParameters();

    PieceType getPieceType();

    PositionValidator getValidator();

    Collection<Move> getMoveDescribers();

    default Collection<Position> getAttackingPositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings captureSettings = getCaptureSettings(currentPosition, piece);
        Map<Move, Set<Position>> attackingPositions = getPossiblePositions(chessBoard, captureSettings);
        return getValidator().keepValidPositionsToAttack(chessBoard, captureSettings, attackingPositions);
    }

    default Collection<Position> getMovePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Map<Move, Set<Position>> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        return getValidator().keepValidPositionsToMove(chessBoard, moveSettings, possiblePositions);
    }

    default MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), getMoveParameters()));
    }

    default MoveSettings getCaptureSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), getCaptureParameters()));
    }

    default Map<Move, Set<Position>> getPossiblePositions(ChessBoard chessBoard, MoveSettings moveSettings) {
        Map<Move, Set<Position>> positions = new HashMap<>();
        for (Map.Entry<Move, Integer> moveDescriber : moveSettings.getSettings().entrySet()) {
            Set<Position> possiblePositions = moveDescriber.getKey().checkMove(chessBoard, moveSettings);
            if (!possiblePositions.isEmpty()) {
                positions.put(moveDescriber.getKey(), possiblePositions);
            }
        }
        return positions;
    }

    default Map<Move, Integer> adaptForPieceColor(PieceColor pieceColor, Map<PieceColor, Collection<Tuple<Move, Integer>>> moveSettings) {
        Map<Move, Integer> moveSettingsForColor = new HashMap<>();
        switch (pieceColor) {
            case WHITE:
                Collection<Tuple<Move, Integer>> tuples = moveSettings.get(pieceColor);
                for (Tuple<Move, Integer> entry : tuples) {
                    moveSettingsForColor.put(entry.getLeft(), entry.getRight());
                }
                return moveSettingsForColor;
            case BLACK:
                Collection<Tuple<Move, Integer>> tuples2 = moveSettings.get(pieceColor);
                for (Tuple<Move, Integer> entry : tuples2) {
                    moveSettingsForColor.put(entry.getLeft(), entry.getRight());
                }
                return moveSettingsForColor;
        }
        return moveSettingsForColor;
    }
}
