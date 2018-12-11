package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PawnMovingRule implements MovingRule {
    @Override
    public List<Position> getPossiblePositions(Piece piece, Position currentPosition) {

        List<Position> possiblePositions = new ArrayList<>();

        File file = currentPosition.getFile();
        Rank rank = currentPosition.getRank();

        //forward diagonal left
//        try {
//            new Position(File.values()[fileIndex-1], )
//        } catch (IndexOutOfBoundsException ex) {
//
//        }

        return getAvailableMoves(getMoveSettings(currentPosition, piece.getPieceColor()));
    }

    private List<Position> getAvailableMoves(MoveSettings moveSettings) {
        Optional<Position> positionDiagLeft = MoveType.FORWARD_DIAGONAL_LEFT.checkMove(moveSettings);
        Optional<Position> positionForward = MoveType.FORWARD.checkMove(moveSettings);
        Optional<Position> positionDiagRight = MoveType.FORWARD_DIAGONAL_RIGHT.checkMove(moveSettings);
       return Stream.of(positionDiagLeft, positionForward, positionDiagRight).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    @Override
    public MoveSettings getMoveSettings(Position currentPosition, PieceColor pieceColor) {
        return new MoveSettings(currentPosition, pieceColor, 1);
    }


    private boolean validPosition(Position position) {


        return false;
    }
}
