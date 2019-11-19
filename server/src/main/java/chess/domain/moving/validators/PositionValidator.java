package chess.domain.moving.validators;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;
import chess.domain.moving.moves.SpecialMove;

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
        Set<PlayerMove> specialMoves = getSpecialMoves(possiblePositions);
        Map<Move, Set<PlayerMove>> nonSpecialMoves = getNonSpecialMoves(possiblePositions);
        Set<PlayerMove> evaluatedMoves = nonSpecialMoves.values().stream()
                .flatMap(Set::stream)
                .filter(playerMove -> chessBoard.q.isNotEmpty(playerMove.getToPosition()))
                .filter(playerMovePredicate -> chessBoard.getModel().get(playerMovePredicate.getToPosition()).getPieceColor().isOppositeColor(selectedPiece.getPieceColor()))
                .map(playerMove -> PlayerMove.of(playerMove, MoveType.CAPTURE))
                .collect(Collectors.toSet());
        evaluatedMoves.addAll(specialMoves);
        return evaluatedMoves;
    }

    default Map<Move, Set<PlayerMove>> getNonSpecialMoves(Map<Move, Set<PlayerMove>> possiblePositions) {
        return possiblePositions.entrySet().stream()
                    .filter(playerMovesPerMove -> !(playerMovesPerMove.getKey() instanceof SpecialMove))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    default Set<PlayerMove> getSpecialMoves(Map<Move, Set<PlayerMove>> possiblePositions) {
        return possiblePositions.entrySet().stream()
                    .filter(playerMovesPerMove -> playerMovesPerMove.getKey() instanceof SpecialMove)
                    .flatMap(playerMovesPerMove -> playerMovesPerMove.getValue().stream())
                    .collect(Collectors.toSet());
    }
}
