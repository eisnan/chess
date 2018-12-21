package app.domain.moving;

import app.domain.*;
import app.domain.moving.rules.SpecialMoveResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnStraightMoveResolver implements SpecialMoveResolver {
    @Override
    public Collection<Position> validate(ChessBoard chessBoard, MoveSettings moveSettings, PieceColor pieceColor, Collection<Position> positions) {

        Rank firstRankForColor = pieceColor == PieceColor.WHITE ? Rank._2 : Rank._7;

        List<Position> validPositions = new ArrayList<>();
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
