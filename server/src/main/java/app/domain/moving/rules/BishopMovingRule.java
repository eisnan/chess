package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.moving.RBQValidator;
import app.domain.moving.moves.BackwardDiagonalLeft;
import app.domain.moving.moves.BackwardDiagonalRight;
import app.domain.moving.moves.ForwardDiagonalLeft;
import app.domain.moving.moves.ForwardDiagonalRight;
import app.domain.util.Tuple;

import java.util.*;

public class BishopMovingRule implements MovingRule {

    private static final Integer BISHOP_LIMIT_POSITIONS = 8;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings = new HashMap<>();
    private PositionValidator invalidator = new RBQValidator();


    public BishopMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardDiagonalLeft(), BISHOP_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), BISHOP_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), BISHOP_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), BISHOP_LIMIT_POSITIONS));
        moveSettings.put(PieceColor.WHITE, legalMoves);
        moveSettings.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Collection<Position> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        Collection<Position> validPositions = new HashSet<>();
        moveSettings.getMovingSettings().keySet().forEach(moveDescriber -> {
            validPositions.addAll(keepValidPositions(chessBoard, moveDescriber, currentPosition, piece, possiblePositions));
        });
        return validPositions;
    }

    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
//        return invalidator.invalidate(chessBoard, currentPosition, selectedPiece, positions);
        return invalidator.keepValidPositions(chessBoard, getMoveSettings(currentPosition, selectedPiece), null);
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveSettings));
    }

    private Collection<Position> getPossiblePositions(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMovingSettings().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
    }
}
