package app.domain.moving;

import app.domain.*;

import java.util.Collection;
import java.util.TreeSet;

public class PawnStraightMoveResolver implements MoveResolver {
    @Override
    public Collection<Position> validate(ChessBoard chessBoard, MoveSettings moveSettings, PieceColor pieceColor, Collection<Position> positions) {

        Rank firstRankForColor = pieceColor == PieceColor.WHITE ? Rank._2 : Rank._7;

        Collection<Position> validPositions = new TreeSet<>();
        Position currentPosition = moveSettings.getCurrentPosition();
        for (Position position : positions) {
            Piece piece = chessBoard.getModel().get(position);
            if (piece == null) {
                validPositions.add(position);
                if (currentPosition.getRank() != firstRankForColor) {
                    break;
                }
            } else {
                break;
            }
        }
        return validPositions;
    }
}
