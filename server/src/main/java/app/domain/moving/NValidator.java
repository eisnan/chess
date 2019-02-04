package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;
import app.domain.comparators.AscendingPositionComparator;
import app.domain.moving.moves.Move;
import app.domain.moving.validators.PositionValidator;

import java.util.*;
import java.util.stream.Collectors;

public class NValidator implements PositionValidator {
    @Override
    public Collection<PlayerMove> keepValidPositionsToMove(ChessBoard chessBoard, MoveSettings moveSettings, Map<Move, Set<PlayerMove>> possiblePositions) {
        return possiblePositions.entrySet().stream()
                .flatMap(moveDescriberPositionsEntry -> moveDescriberPositionsEntry.getValue().stream())
                .filter(playerMove -> {
                    Piece piece = chessBoard.getModel().get(playerMove.getToPosition());
                    return piece == null || piece.getPieceColor() != moveSettings.getPiece().getPieceColor();
                })
                .map(playerMove -> new PlayerMove(playerMove, MoveType.NORMAL))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new AscendingPositionComparator())));
    }
}
