package chess.domain;

import chess.domain.moving.PlayerMover;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttackResolverTest {

    private AttackResolver attackResolver = new AttackResolver();
    private PlayerMover mover = new PlayerMover();

    @Test
    public void whoIsAttackingPosition() {

        ChessBoard chessBoard = new ChessBoard();
        mover.move(chessBoard, Position.of("d2"), Position.of("d4"));
        mover.move(chessBoard, Position.of("c1"), Position.of("f4"));


        attackResolver.whoIsAttackingPosition(chessBoard, Position.of("c7"));

    }
}