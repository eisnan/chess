package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.util.Triple;
import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class ChessBoardIT {

    private PositionResolver positionResolver = new PositionResolver();

    public List<String> loadFromFile(String file) throws IOException {
        return Resources.readLines(Resources.getResource(file), Charset.defaultCharset());
    }

    @Test
    public void testGame1() throws IOException {

        ChessBoard chessBoard = new ChessBoard();
        List<String> lines = loadFromFile("game.it");

        for (String line : lines) {
            Triple<Position, Collection<PlayerMove>, Position> fromPossibleTo = parseLine(line);
            System.out.println(fromPossibleTo);

            Collection<PlayerMove> validMoves = positionResolver.getValidMoves(chessBoard, fromPossibleTo.getLeft());

        }

    }

    private Triple<Position, Collection<PlayerMove>, Position> parseLine(String line) {
        List<String> split = Stream.of(line.split("->")).map(String::trim).collect(Collectors.toList());

        Position from = Position.of(split.get(0));

        Collection<PlayerMove> possible = parsePlayerMove(split.get(1));

        Position to = Position.of(split.get(2));

        return Triple.of(from, possible, to);
    }

    private Collection<PlayerMove> parsePlayerMove(String s) {

        String[] pMoves = s.split(",");

//        Stream.of(pMoves).map(str -> new PlayerMove())
return null;

    }

}