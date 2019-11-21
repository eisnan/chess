package chess.domain.moving.validators;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;
import chess.domain.moving.MoveSettings;

import java.util.*;

public class RBQValidator implements PositionValidator {

    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        List<PlayerMove> validPositions = new ArrayList<>();
        Piece selectedPiece = moveSettings.getPiece();

        possiblePositions.forEach((moveDescriber, positions) -> {
            validPositions.addAll(keepValid(chessBoard, selectedPiece, positions));
        });
        return validPositions;
    }

    @Override
    public Collection<PlayerMove> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        return keepValidPositionsToMove(chessBoard, moveSettings, possiblePositions);
    }

    private Collection<PlayerMove> keepValid(ChessBoard chessBoard, Piece selectedPiece, Collection<PlayerMove> playerMoves) {
        List<PlayerMove> validPlayerMoves = new ArrayList<>();
        for (PlayerMove playerMove : playerMoves) {
            Piece piece = chessBoard.getModel().get(playerMove.getToPosition());
            if (piece == null) {
                validPlayerMoves.add(playerMove);
            } else if (selectedPiece.getPieceColor() != piece.getPieceColor()) {
                validPlayerMoves.add(PlayerMove.of(playerMove, MoveType.ATTACK));
                break;
            } else {
                break;
            }
        }
        return validPlayerMoves;
    }
}
