package chess.domain;

import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRules;
import chess.domain.util.Tuple;
import chess.domain.moving.moves.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PositionInterpreter {


    public Collection<IterableMove> getAttackDirections(ChessBoard chessBoard, PieceColor pieceColor, Position kingPosition, Set<Position> vulnerablePositions) {

        Collection<IterableMove> attackDirections = new HashSet<>();
        int fileOrdinal = kingPosition.getFile().ordinal();
        int rankOrdinal = kingPosition.getRank().ordinal();

        vulnerablePositions.forEach(position -> {

            int vulFileOrdinal = position.getFile().ordinal();
            int vulRankOrdinal = position.getRank().ordinal();

            if (vulFileOrdinal < fileOrdinal) { //left
                if (vulRankOrdinal < rankOrdinal) { // down
                    attackDirections.add(new BackwardDiagonalLeft());
                } else if (vulRankOrdinal == rankOrdinal) {
                    attackDirections.add(new LeftMove());
                } else { //up
                    attackDirections.add(new ForwardDiagonalLeft());
                }
            } else if (vulFileOrdinal == fileOrdinal) {
                if (vulRankOrdinal < rankOrdinal) {
                    attackDirections.add(new BackwardMove());
                } else if (vulRankOrdinal == rankOrdinal) { //same position
                    //do nothing
                } else {
                    attackDirections.add(new ForwardMove());
                }
            } else {
                if (vulRankOrdinal < rankOrdinal) {
                    attackDirections.add(new BackwardDiagonalRight());
                } else if (vulRankOrdinal == rankOrdinal) { //same position
                    attackDirections.add(new RightMove());
                } else {
                    attackDirections.add(new ForwardDiagonalRight());
                }
            }


        });

        return attackDirections;
    }

    public Optional<Tuple<Position, Piece>> findFirstPieceOnDirection(ChessBoard chessBoard, IterableMove move, Piece piece, Position startingPosition) {
        return move.checkMove(chessBoard, MoveSettings.getMaxSettings(startingPosition, piece, move)).stream()
                .map(PlayerMove::getToPosition)
                .filter(position -> chessBoard.get(position) != null)
                .map(position -> Tuple.of(position, chessBoard.get(position)))
                .findFirst();
    }

    public boolean isOnAttackingDirection(Move move, Piece attackingPiece) {
        Collection<PieceType> pieceTypes = MovingRules.findWhichPiecesCanAttackOnThisDirection(move);
        return pieceTypes.contains(attackingPiece.getPieceType());
    }
}
