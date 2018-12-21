package app.domain.moving.rules;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.MoveDescriber;
import app.domain.moving.MoveSettings;
import app.domain.moving.PositionValidator;
import app.domain.moving.moves.*;
import app.domain.util.Tuple;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PawnMovingRule implements MovingRule {

    private PositionValidator validator = new PawnValidator();

    private Map<PieceColor, Collection<Tuple<MoveDescriber, Integer>>> moveParameters = new HashMap<>();

    public PawnMovingRule() {
        moveParameters.put(PieceColor.WHITE, Arrays.asList(new Tuple<>(new ForwardMove(), 2),
                new Tuple<>(new ForwardDiagonalLeft(), 1),
                new Tuple<>(new ForwardDiagonalRight(), 1)));
        moveParameters.put(PieceColor.BLACK, Arrays.asList(new Tuple<>(new BackwardMove(), 2),
                new Tuple<>(new BackwardDiagonalLeft(), 1),
                new Tuple<>(new BackwardDiagonalRight(), 1)));
    }

    @Override
    public Collection<Position> getAvailablePositions(ChessBoard chessBoard, Piece piece, Position currentPosition) {
        MoveSettings moveSettings = getMoveSettings(currentPosition, piece);
        Map<MoveDescriber, Collection<Position>> possiblePositions = getPossiblePositions(chessBoard, moveSettings);
        return validator.keepValidPositions(chessBoard, moveSettings, possiblePositions);
    }


    private Map<MoveDescriber, Collection<Position>> getPossiblePositions(ChessBoard chessBoard, MoveSettings moveSettings) {
        Map<MoveDescriber, Collection<Position>> positions = new HashMap<>();
        for (Map.Entry<MoveDescriber, Integer> moveDescriber : moveSettings.getMovingSettings().entrySet()) {
            Collection<Position> possiblePositions = moveDescriber.getKey().checkMove(chessBoard, moveSettings);
            if (!possiblePositions.isEmpty()) {
                positions.put(moveDescriber.getKey(), possiblePositions);
            }
        }
        return positions;
    }

    public MoveSettings getMoveSettings(Position currentPosition, Piece piece) {
        return new MoveSettings(currentPosition, piece, this, adaptForPieceColor(piece.getPieceColor(), moveParameters));
    }


}
