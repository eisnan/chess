package chess.domain.moving.validators;

import chess.domain.ChessBoard;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gabs on 1/31/2019.
 */
public class KPositionValidator implements PositionValidator {
    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        return null;
    }

    @Override
    public Collection<PlayerMove> keepValidPositionsToAttack(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        return null;
    }
}
