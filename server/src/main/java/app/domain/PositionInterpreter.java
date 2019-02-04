package app.domain;

import app.domain.moving.PlayerMove;
import app.domain.moving.moves.*;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

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

    public Optional<Tuple<Position, Piece>> findFirstPieceOnDirection(ChessBoard chessBoard, IterableMove move, Collection<PlayerMove> positions) {
//        Collection<PlayerMove> direction = new TreeSet<>(move.getPositionComparator());
//        direction.addAll(positions);
//
//        Optional<Tuple<Position, Piece>> first = direction.stream().filter(position -> chessBoard.getModel().get(position) != null).map(position -> new Tuple<>(position,chessBoard.getModel().get(position))).findFirst();
//
//        return first;
        return null;
    }

    public boolean isOnAttackingDirection(Move move, Piece attackingPiece) {
        Collection<PieceType> pieceTypes = MovingRules.findWhichPiecesCanAttackOnThisDirection(move);
        return pieceTypes.contains(attackingPiece.getPieceType());
    }
}
