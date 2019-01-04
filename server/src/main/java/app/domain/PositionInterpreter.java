package app.domain;

import app.domain.moving.MoveDescriber;
import app.domain.moving.moves.*;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

import java.util.*;

public class PositionInterpreter {


    public Collection<MoveDescriber> getAttackDirections(ChessBoard chessBoard, PieceColor pieceColor, Position kingPosition, Set<Position> vulnerablePositions) {

        Collection<MoveDescriber> attackDirections = new HashSet<>();
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

    public Optional<Tuple<Position, Piece>> findFirstPieceOnDirection(ChessBoard chessBoard, MoveDescriber moveDescriber, Collection<Position> positions) {
        Collection<Position> direction = new TreeSet<>(moveDescriber.getPositionComparator());
        direction.addAll(positions);

        Optional<Tuple<Position, Piece>> first = direction.stream().filter(position -> chessBoard.getModel().get(position) != null).map(position -> new Tuple<>(position,chessBoard.getModel().get(position))).findFirst();

        return first;
    }

    public boolean isOnAttackingDirection(MoveDescriber moveDescriber, Piece attackingPiece) {
        Collection<PieceType> pieceTypes = MovingRules.findWhichPiecesCanAttackOnThisDirection(moveDescriber);
        return pieceTypes.contains(attackingPiece.getPieceType());
    }
}
