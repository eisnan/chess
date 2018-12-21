package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MovingRule {
    Collection<Position> getPossiblePositions(ChessBoard chessBoard, Piece piece, Position currentPosition);

    Collection<Position> removeInvalidPositions(ChessBoard chessBoard, Position currentPosition, Piece selectedPiece, Collection<Position> positions);



    default Map<MoveDescriber, Integer> adapt(PieceColor pieceColor, Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings) {
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
