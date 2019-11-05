package chess.api;

import chess.domain.*;
import chess.domain.comparators.AscendingPositionComparator;
import chess.domain.moving.AlgebraicNotationLoader;
import chess.domain.moving.PlayerMover;
import chess.domain.moving.PlayerMove;
import chess.domain.util.ChessboardSingleton;
import chess.mapper.ChessboardHtmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/raw/html/board")
public class RawHtmlBoardController {

    private ChessboardHtmlMapper mapper;
    private Map<UUID, ChessBoard> gameMemory = ChessboardSingleton.INSTANCE.getGameMemory();

    private final AlgebraicNotationLoader algLoader;

    @Autowired
    public RawHtmlBoardController(ChessboardHtmlMapper mapper, AlgebraicNotationLoader algLoader) {
        this.mapper = mapper;
        this.algLoader = algLoader;
    }

    @RequestMapping("/getFiles")
    public File[] getFiles() {
        return File.values();
    }

    @RequestMapping("/getRanks")
    public String[] getRanks() {
        return Rank.stringValues();
    }

    @GetMapping("loadFileGame")
    public String loadFileGame(@RequestParam("file") String file) throws IOException {
        ChessBoard chessBoard = algLoader.loadFromFile(file);
        UUID newGameId = UUID.randomUUID();
        gameMemory.put(newGameId, chessBoard);
        return mapper.toPage(chessBoard);
    }

    @GetMapping("startGame")
    public String startGame() {
        UUID newGameId = UUID.randomUUID();
        gameMemory.put(newGameId, new ChessBoard());
        return newGameId.toString();
    }

    @GetMapping
    public String getBoard(@RequestParam("gameId") String gameId) {
        ChessBoard chessBoard = gameMemory.get(UUID.fromString(gameId));
        return mapper.toPage(chessBoard);
    }

    @GetMapping("checkPosition")
    public String checkPosition(@RequestParam("gameId") String gameId, @RequestParam("fromPosition") String fromStringPosition) {
        ChessBoard chessBoard = gameMemory.get(UUID.fromString(gameId));
        Position fromPosition = new Position(fromStringPosition);
        Collection<PlayerMove> availablePositions = new PositionResolver().getAvailableMoves(chessBoard, fromPosition);
        Set<String> collect = availablePositions.stream()
                .sorted(new AscendingPositionComparator())
                .map(PlayerMove::getToPosition)
                .map(Position::getAlgebraicNotation)
                .collect(Collectors.toSet());
        return collect.toString();
    }

    @PutMapping("move")
    public String moveWhite(@RequestParam("gameId") String gameId, @RequestParam("fromPosition") String fromStringPosition, @RequestParam("toPosition") String toStringPosition) {
        ChessBoard chessBoard = gameMemory.get(UUID.fromString(gameId));
        Position fromPosition = new Position(fromStringPosition);
        Piece selectedPiece = chessBoard.get(fromPosition);
        Position toPosition = new Position(toStringPosition);
        new PlayerMover().move(chessBoard, selectedPiece, fromPosition, toPosition);

        return mapper.toPage(chessBoard);
    }
}

