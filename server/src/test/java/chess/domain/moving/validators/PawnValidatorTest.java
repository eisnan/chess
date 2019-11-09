package chess.domain.moving.validators;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.PositionResolver;
import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PawnValidatorTest {

    private PositionResolver positionResolver = new PositionResolver();

    @Test
    public void keepValidPositionsToMove_openingMove() {

        ChessBoard chessBoard = new ChessBoard();
        Position e2 = Position.of("e2");
        Collection<PlayerMove> validMoves = positionResolver.getValidMoves(chessBoard, e2);
        assertTrue(validMoves.stream().allMatch(playerMove -> playerMove.getMoveType() == MoveType.MOVE));
        assertTrue(validMoves.stream().allMatch(playerMove -> playerMove.getFromPosition().equals(e2)));

        Collection<Position> expected = Position.of("e3","e4");
        Collection<Position> received = validMoves.stream().map(PlayerMove::getToPosition).collect(Collectors.toSet());
        assertTrue(received.containsAll(expected) && expected.containsAll(received));
    }


    @Test
    public void keepValidPositionsToMove_noMove() {

        ChessBoard chessBoard = new ChessBoard();
        Position e2 = Position.of("e2");
        Collection<PlayerMove> validMoves = positionResolver.getValidMoves(chessBoard, e2);
        assertTrue(validMoves.stream().allMatch(playerMove -> playerMove.getMoveType() == MoveType.MOVE));
        assertTrue(validMoves.stream().allMatch(playerMove -> playerMove.getFromPosition().equals(e2)));

        Collection<Position> expected = Position.of("e3","e4");
        Collection<Position> received = validMoves.stream().map(PlayerMove::getToPosition).collect(Collectors.toSet());
        assertTrue(received.containsAll(expected) && expected.containsAll(received));
    }

    @Test
    public void keepValidPositionsToAttack() {
    }

    @Test
    public void isEnPassant() {
    }
}