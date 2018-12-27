package app.domain.moving.rules;

import app.domain.*;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface MovingRule {
    Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> getMoveParameters();

    Map<PieceColor, Collection<MoveDescriber>> getCapturingMoves();

    PieceType getPieceType();

    PositionValidator getValidator();

    default Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Map<MoveDescriber, Collection<Position>> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        return getValidator().keepValidPositions(chessBoard, moveSettings, possiblePositions);
    }

    default MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), getMoveParameters()));
    }

    default Map<MoveDescriber, Collection<Position>> getPossiblePositions(ChessBoard chessBoard, MoveSettings moveSettings) {
        Map<MoveDescriber, Collection<Position>> positions = new HashMap<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMovingSettings().entrySet()) {
            Collection<Position> possiblePositions = moveDescriber.getKey().checkMove(chessBoard, moveSettings);
            if (!possiblePositions.isEmpty()) {
                positions.put(moveDescriber.getKey(), possiblePositions);
            }
        }
        return positions;
    }

    default Map<MoveDescriber, Integer> adaptForPieceColor(PieceColor pieceColor, Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings) {
        Map<MoveDescriber, Integer> moveSettingsForColor = new HashMap<>();
        switch (pieceColor) {
            case WHITE:
                Collection<Tuple<MoveDescriber, Integer>> tuples = moveSettings.get(pieceColor);
                for (Tuple<MoveDescriber, Integer> entry : tuples) {
                    moveSettingsForColor.put(entry.getLeft(), entry.getRight());
                }
                return moveSettingsForColor;
            case BLACK:
                Collection<Tuple<MoveDescriber, Integer>> tuples2 = moveSettings.get(pieceColor);
                for (Tuple<MoveDescriber, Integer> entry : tuples2) {
                    moveSettingsForColor.put(entry.getLeft(), entry.getRight());
                }
                return moveSettingsForColor;
        }
        return moveSettingsForColor;
    }
}
