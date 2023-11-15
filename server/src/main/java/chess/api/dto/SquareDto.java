package chess.api.dto;

import chess.api.dto.serializers.SquareSerializer;
import chess.domain.Piece;
import chess.domain.PieceColor;
import chess.domain.Position;
import chess.domain.SquareColor;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Gabs on 5/12/2019.
 */
@Data
@AllArgsConstructor
@JsonSerialize(using = SquareSerializer.class)
public class SquareDto {
    private SquareColor squareColor;
    private Position position;
    private Piece piece;
}
