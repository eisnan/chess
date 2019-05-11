package chess.api;

import chess.api.dto.ChessBoardDto;
import chess.domain.ChessBoard;
import chess.mapper.ChessBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private ChessBoardMapper mapper;

    @GetMapping
    public ChessBoardDto getBoard() {
        ChessBoard chessBoard = new ChessBoard();
        return mapper.toDto(chessBoard);
    }
}
