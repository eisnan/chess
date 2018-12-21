package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.Position;
import app.domain.moving.rules.MovingRule;

import java.util.Collection;
import java.util.Collections;

public interface SpecialMovingRule extends MovingRule {

    Collection<Position> removeInvalidPositions(ChessBoard chessBoard, MoveDescriber moveDescriber, Position currentPosition, Piece selectedPiece, Collection<Position> positions);

    @Override
    default Collection<Position> removeInvalidPositions(ChessBoard chessBoard, Position currentPosition, Piece selectedPiece, Collection<Position> positions) {
        return Collections.emptySet();
    }
}
