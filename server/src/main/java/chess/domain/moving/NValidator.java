package chess.domain.moving;

import chess.domain.ChessBoard;
import chess.domain.Piece;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.moving.moves.Move;
import chess.domain.moving.validators.PositionValidator;

import java.util.*;
import java.util.stream.Collectors;

public class NValidator implements PositionValidator {
    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        return possiblePositions.entrySet().stream()
                .flatMap(moveDescriberPositionsEntry -> moveDescriberPositionsEntry.getValue().stream())
                .filter(playerMove -> {
                    Piece piece = chessBoard.getModel().get(playerMove.getToPosition());
                    return piece == null;
                })
                .map(playerMove -> new PlayerMove(playerMove, MoveType.MOVE))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new AscendingPositionComparator())));
    }
}
