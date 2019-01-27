package app.domain.moving.validators;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.MoveSettings;
import app.domain.moving.moves.Move;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Collectors;

public interface PositionValidator {

    Collection<Position> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, SortedSet<Position>> possiblePositions);

    /**
     * Default implementation returns the positions where there are enemy pieces.
     */
    default Collection<Position> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, SortedSet<Position>> possiblePositions) {
        Piece selectedPiece = moveSettings.getPiece();
        return possiblePositions.values().stream().flatMap(SortedSet::stream)
                .filter(chessBoard::isNotEmpty)
                .filter(position -> chessBoard.getModel().get(position).getPieceColor().isOppositeColor(selectedPiece.getPieceColor()))
                .collect(Collectors.toList());
    }
}
