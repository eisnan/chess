package chess.domain;

import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;
import chess.domain.moving.PlayerMover;
import chess.domain.util.Triple;
import com.google.common.io.Resources;
import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ChessBoardIT {

    private PositionResolver positionResolver = new PositionResolver();
    private ChessBoard chessBoard;
    private PlayerMover mover = new PlayerMover();
    private CheckEvaluator checkEvaluator = new CheckEvaluator();

    @Before
    public void setUp() throws Exception {
        this.chessBoard = new ChessBoard();
    }

    public List<String> loadFromFile(String file) throws IOException {
        return Resources.readLines(Resources.getResource(file), Charset.defaultCharset());
    }

    @Test
    public void testHash() {
        Set<PlayerMove> set = new HashSet<>();
        PlayerMove p1 = PlayerMove.of(Piece.getWhitePiece(PieceType.BISHOP), Position.of("f1"), Position.of("b5"), MoveType.CHECK);

        set.add(p1);


        Collection<PlayerMove> list = new ArrayList<>();
        list.add(PlayerMove.of(Piece.getWhitePiece(PieceType.BISHOP), Position.ofValid(File.f, Rank._1), Position.of("b5"), MoveType.CHECK));
        assertTrue(set.containsAll(list));
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

            boolean pinnedPiece = checkEvaluator.isPinnedPiece(chessBoard, fromPossibleTo.getLeft());
            System.out.println(pinnedPiece);
            for (PlayerMove playerMove : validMoves) {

                boolean checkMove = checkEvaluator.isCheckMove(chessBoard, playerMove);

                if (checkMove) {
                    playerMove.setMoveType(MoveType.CHECK);
                }

                System.out.println(checkMove);
            }


            assertTrue(fromPossibleTo.getMiddle().containsAll(validMoves) && validMoves.containsAll(fromPossibleTo.getMiddle()));


            List<PlayerMove> matchMoves = fromPossibleTo.getMiddle().stream().filter(playerMove -> playerMove.getToPosition() == fromPossibleTo.getRight()).collect(Collectors.toList());
            if (matchMoves.size() != 1) {
                throw new AssertionError();
            }
            PlayerMove moveToMake = matchMoves.get(0);
            mover.move(chessBoard, moveToMake);

        }

    }

    @Test
    public void testGame() throws IOException {
        GameId gameId = new GameId();
        ChessBoard chessBoard = new ChessBoard();
        ChessPlayer whitePlayer = new ChessPlayer("Gabs");
        ChessPlayer blackPlayer = new ChessPlayer("Horia");
        ChessGame chessGame = new ChessGame(gameId, whitePlayer, blackPlayer);

        ChessEngine engine = chessGame.startGame();

        while (!engine.checkMate()) {

            PieceColor colorToMove = engine.getColorToMove();
            ChessPlayer playerToMove = chessGame.getPlayerByColor(colorToMove);

            System.out.println("Player to move: " + playerToMove);


            List<String> lines = loadFromFile("game.it");

            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Line number " + i);
                String line = lines.get(i);
                Triple<Position, Collection<PlayerMove>, Position> fromPossibleTo = parseLine(line);
                System.out.println(fromPossibleTo);

                Collection<PlayerMove> validMoves = positionResolver.getValidMoves(chessBoard, fromPossibleTo.getLeft());

                boolean pinnedPiece = checkEvaluator.isPinnedPiece(chessBoard, fromPossibleTo.getLeft());
                System.out.println(pinnedPiece);
                for (PlayerMove playerMove : validMoves) {

                    boolean checkMove = checkEvaluator.isCheckMove(chessBoard, playerMove);

                    if (checkMove) {
                        playerMove.setMoveType(MoveType.CHECK);
                    }

                    System.out.println(checkMove);
                }


                assertTrue(fromPossibleTo.getMiddle().containsAll(validMoves) && validMoves.containsAll(fromPossibleTo.getMiddle()));


                List<PlayerMove> matchMoves = fromPossibleTo.getMiddle().stream().filter(playerMove -> playerMove.getToPosition() == fromPossibleTo.getRight()).collect(Collectors.toList());
                if (matchMoves.size() != 1) {
                    throw new AssertionError();
                }
                PlayerMove moveToMake = matchMoves.get(0);
                mover.move(chessBoard, moveToMake);

            }
        }

    }

    private Triple<Position, Collection<PlayerMove>, Position> parseLine(String line) {
        List<String> split = Stream.of(line.split("->")).map(String::trim).collect(Collectors.toList());
        Position from = Position.of(split.get(0));
        String[] pMoves = split.get(1).split(";");

        List<PlayerMove> possible = Stream.of(pMoves).map(str -> {
            List<PlayerMove> parsedMoves = new ArrayList<>();

            String[] doubleMoves = str.split("<>");

            String[] pmove = doubleMoves[0].split(",");
            Piece piece = chessBoard.get(from);
            Position tPos = Position.of(pmove[0]);
            MoveType moveType = MoveType.valueOf(pmove[1].toUpperCase());
            parsedMoves.add(PlayerMove.of(piece, from, tPos, moveType));

            if (doubleMoves.length > 1) { //castling
                String[] pmoveRook = doubleMoves[1].split(",");
                Position rookPosFrom = Position.of(pmoveRook[0]);
                Position rookPosTo = Position.of(pmoveRook[2]);
                Piece rook = chessBoard.get(rookPosFrom);
                MoveType castlingType = MoveType.valueOf(pmoveRook[1].toUpperCase());
                parsedMoves.add(PlayerMove.of(rook, rookPosFrom, rookPosTo, castlingType));
            }
            return parsedMoves;

        }).flatMap(Collection::stream).collect(Collectors.toList());

        Position to = Position.of(split.get(2));
        return Triple.of(from, possible, to);
    }


}