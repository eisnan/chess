package chess.domain.moving.rules;

import chess.domain.*;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.moves.Move;
import chess.domain.moving.validators.PositionValidator;
import chess.domain.util.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface MovingRule {
    Map<PieceColor, Collection<Pair<Move, Integer>>> getMoveParameters();

    Map<PieceColor, Collection<Pair<Move, Integer>>> getCaptureParameters();

    PieceType getPieceType();

    PositionValidator getValidator();

    Collection<Move> getMoveDescribers();

    default Collection<PlayerMove> getAttackingPositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings captureSettings = getCaptureSettings(currentPosition, piece);
        Map<Move, Set<PlayerMove>> attackingPositions = getPossiblePositions(chessBoard, captureSettings);
        return getValidator().keepValidPositionsToAttack(chessBoard, captureSettings, attackingPositions);
    }

    default Collection<PlayerMove> getMovePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Map<Move, Set<PlayerMove>> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        return getValidator().keepValidPositionsToMove(chessBoard, moveSettings, possiblePositions);
    }

    default MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, adaptForPieceColor(piece.getPieceColor(), getMoveParameters()));
    }

    default MoveSettings getCaptureSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, adaptForPieceColor(piece.getPieceColor(), getCaptureParameters()));
    }

    default Map<Move, Set<PlayerMove>> getPossiblePositions(ChessBoard chessBoard, MoveSettings moveSettings) {
        Map<Move, Set<PlayerMove>> positions = new HashMap<>();
        for (Map.Entry<Move, Integer> moveDescriber : moveSettings.getSettings().entrySet()) {
            Set<PlayerMove> possiblePositions = moveDescriber.getKey().checkMove(chessBoard, moveSettings);
            if (!possiblePositions.isEmpty()) {
                positions.put(moveDescriber.getKey(), possiblePositions);
            }
        }
        return positions;
    }

    default Map<Move, Integer> adaptForPieceColor(PieceColor pieceColor, Map<PieceColor, Collection<Pair<Move, Integer>>> moveSettings) {
        Map<Move, Integer> moveSettingsForColor = new HashMap<>();
        switch (pieceColor) {
            case WHITE:
                Collection<Pair<Move, Integer>> pairs = moveSettings.get(pieceColor);
                for (Pair<Move, Integer> entry : pairs) {
                    moveSettingsForColor.put(entry.getLeft(), entry.getRight());
                }
                return moveSettingsForColor;
            case BLACK:
                Collection<Pair<Move, Integer>> tuples2 = moveSettings.get(pieceColor);
                for (Pair<Move, Integer> entry : tuples2) {
                    moveSettingsForColor.put(entry.getLeft(), entry.getRight());
                }
                return moveSettingsForColor;
        }
        return moveSettingsForColor;
    }
}
