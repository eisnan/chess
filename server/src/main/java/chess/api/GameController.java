package chess.api;

import chess.api.dto.GameDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

    public GameDto newGame() {
        return new GameDto();
    }

    public GameDto loadGame() {
        return null;
    }
}
