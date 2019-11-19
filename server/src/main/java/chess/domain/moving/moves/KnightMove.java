package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.InvalidPositionException;
import chess.domain.Piece;
import chess.domain.Position;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class KnightMove implements Move {

    @Override
    public Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
        Set<PlayerMove> aboveMoves = aboveMoves(moveSettings.getCurrentPosition(), moveSettings.getPiece());
        aboveMoves.addAll(belowMoves(moveSettings.getCurrentPosition(), moveSettings.getPiece()));

        return aboveMoves;
    }

    private Set<PlayerMove> belowMoves(Position currentPosition, Piece piece) {
        Set<Position> belowPositions = new HashSet<>();
        int fileOrdinal = currentPosition.getFile().ordinal();
        int rankOrdinal = currentPosition.getRank().ordinal();
        Position.of(fileOrdinal - 2, rankOrdinal - 1).ifPresent(belowPositions::add);
        Position.of(fileOrdinal - 1, rankOrdinal - 2).ifPresent(belowPositions::add);
        Position.of(fileOrdinal + 2, rankOrdinal - 1).ifPresent(belowPositions::add);
        Position.of(fileOrdinal + 1, rankOrdinal - 2).ifPresent(belowPositions::add);

        return belowPositions.stream()
                .map(position -> PlayerMove.of(piece, currentPosition, position))
                .collect(Collectors.toSet());
    }

    private Set<PlayerMove> aboveMoves(Position currentPosition, Piece piece) {
        Set<Position> abovePositions = new HashSet<>();
        int fileOrdinal = currentPosition.getFile().ordinal();
        int rankOrdinal = currentPosition.getRank().ordinal();
        Position.of(fileOrdinal - 2, rankOrdinal + 1).ifPresent(abovePositions::add);
        Position.of(fileOrdinal - 1, rankOrdinal + 2).ifPresent(abovePositions::add);
        Position.of(fileOrdinal + 2, rankOrdinal + 1).ifPresent(abovePositions::add);
        Position.of(fileOrdinal + 1, rankOrdinal + 2).ifPresent(abovePositions::add);

        return abovePositions.stream()
                .map(position -> PlayerMove.of(piece, currentPosition, position))
                .collect(Collectors.toSet());
    }
}
