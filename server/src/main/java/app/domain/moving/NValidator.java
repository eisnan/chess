package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class NValidator implements PositionValidator {
    @Override
    public Collection<Position> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        return possiblePositions.entrySet().stream().flatMap(moveDescriberPositionsEntry -> moveDescriberPositionsEntry.getValue().stream()).filter(position -> {
            Piece piece = chessBoard.getModel().get(position);
            return piece == null || piece.getPieceColor() != moveSettings.getPiece().getPieceColor();
        }).collect(Collectors.toCollection(() -> new TreeSet<>(new AscendingPositionComparator())));
    }

    @Override
    public Collection<Position> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<MoveDescriber, Collection<Position>> possiblePositions) {
        return null;
    }
}