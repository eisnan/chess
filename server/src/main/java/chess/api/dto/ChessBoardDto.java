package chess.api.dto;

import chess.api.dto.serializers.ChessBoardSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Gabs on 5/11/2019.
 */
@Data
@JsonSerialize(using = ChessBoardSerializer.class)
public class ChessBoardDto {
    private String boardId;
    private Map<PositionDto, PieceDto> model = new LinkedHashMap<>();

}
