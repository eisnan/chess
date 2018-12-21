package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.moving.RBQValidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.*;

public class KingMovingRule implements MovingRule {

    private static final Integer KING_LIMIT_POSITIONS = 1;
    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveSettings = new HashMap<>();
    private PositionValidator validator = new RBQValidator();

    public KingMovingRule() {
        Collection<Tuple<MoveDescriber, Integer>> legalMoves = Arrays.asList(
                new Tuple<>(new ForwardMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new LeftMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new RightMove(), KING_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalLeft(), KING_LIMIT_POSITIONS),
                new Tuple<>(new ForwardDiagonalRight(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalLeft(), KING_LIMIT_POSITIONS),
                new Tuple<>(new BackwardDiagonalRight(), KING_LIMIT_POSITIONS));
        moveSettings.put(PieceColor.WHITE, legalMoves);
        moveSettings.put(PieceColor.BLACK, legalMoves);
    }

    @Override
    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        return getAvailableMoves(chessBoard, getMoveSettings(currentPosition, piece));
    }

    public Collection<Position> keepValidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
//        return validator.invalidate(chessBoard, currentPosition, selectedPiece, positions);
        return validator.keepValidPositions(chessBoard, getMoveSettings(currentPosition, selectedPiece), null);

    }


    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveSettings));
    }

    private Collection<Position> getAvailableMoves(ChessBoard chessBoard, MoveSettings moveSettings) {
        Collection<Position> positions = new TreeSet<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMovingSettings().entrySet()) {
            positions.addAll(moveDescriber.getKey().checkMove(chessBoard, moveSettings));
        }
        return positions;
    }
}
