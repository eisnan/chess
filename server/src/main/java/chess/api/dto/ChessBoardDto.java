package chess.api.dto;

import chess.domain.Piece;
import chess.domain.Position;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Gabs on 5/11/2019.
 */
@Data
public class ChessBoardDto {
    private Map<Position, Piece> model = new LinkedHashMap<>();

}
