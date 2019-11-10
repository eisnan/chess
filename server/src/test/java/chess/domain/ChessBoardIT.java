package chess.domain;

import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.PlayerMover;
import chess.domain.util.Triple;
import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ChessBoardIT {

    private PositionResolver positionResolver = new PositionResolver();
    private ChessBoard chessBoard;
    private PlayerMover mover = new PlayerMover();

    @Before
    public void setUp() throws Exception {
        this.chessBoard = new ChessBoard();
    }

    public List<String> loadFromFile(String file) throws IOException {
        return Resources.readLines(Resources.getResource(file), Charset.defaultCharset());
    }

    @Test
    public void testGame1() throws IOException {

        List<String> lines = loadFromFile("game.it");

        for (int i = 0; i < lines.size(); i++) {
            System.out.println("Line number " + i);
            String line = lines.get(i);
            Triple<Position, Collection<PlayerMove>, Position> fromPossibleTo = parseLine(line);
            System.out.println(fromPossibleTo);

            Collection<PlayerMove> validMoves = positionResolver.getValidMoves(chessBoard, fromPossibleTo.getLeft());

            assertTrue(fromPossibleTo.getMiddle().containsAll(validMoves) && validMoves.containsAll(fromPossibleTo.getMiddle()));


            List<PlayerMove> matchMoves = fromPossibleTo.getMiddle().stream().filter(playerMove -> playerMove.getToPosition() == fromPossibleTo.getRight()).collect(Collectors.toList());
            if (matchMoves.size() != 1) {
                throw new AssertionError();
            }
            PlayerMove moveToMake = matchMoves.get(0);
            mover.move(chessBoard, moveToMake);

        }

    }

    private Triple<Position, Collection<PlayerMove>, Position> parseLine(String line) {
        List<String> split = Stream.of(line.split("->")).map(String::trim).collect(Collectors.toList());
        Position from = Position.of(split.get(0));
        String[] pMoves = split.get(1).split(";");

        List<PlayerMove> possible = Stream.of(pMoves).map(str -> {
            String[] pmove = str.split(",");
            Piece piece = chessBoard.get(from);
            Position tPos = Position.of(pmove[0]);
            MoveType moveType = MoveType.valueOf(pmove[1]);
            return new PlayerMove(piece, from, tPos, moveType);
        }).collect(Collectors.toList());

        Position to = Position.of(split.get(2));
        return Triple.of(from, possible, to);
    }


}