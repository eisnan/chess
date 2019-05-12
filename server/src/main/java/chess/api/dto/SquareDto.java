package chess.api.dto;

import chess.api.dto.serializers.SquareSerializer;
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
    private ColorDto squareColor;
    private PositionDto position;
    private PieceDto piece;
}
