package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface MovingRule {
    Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition);

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
