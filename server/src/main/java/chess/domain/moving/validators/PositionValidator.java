package chess.domain.moving.validators;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface PositionValidator {

    Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions);

    /**
     * Default implementation returns the positions where there are enemy pieces.
     */
    default Collection<PlayerMove> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        Piece selectedPiece = moveSettings.getPiece();
        return possiblePositions.values().stream().flatMap(Set::stream)
                .filter(playerMove -> chessBoard.q.isNotEmpty(playerMove.getToPosition()))
                .filter(playerMovePredicate -> chessBoard.getModel().get(playerMovePredicate.getToPosition()).getPieceColor().isOppositeColor(selectedPiece.getPieceColor()))
                .map(playerMove -> new PlayerMove(playerMove, MoveType.CAPTURE))
                .collect(Collectors.toList());
    }
}
