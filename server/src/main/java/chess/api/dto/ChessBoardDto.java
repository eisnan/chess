package chess.api.dto;

import chess.api.dto.serializers.ChessBoardSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
//@JsonSerialize(using = ChessBoardSerializer.class)
public class ChessBoardDto {
    private String boardId;
    private SquareDto[][] model = new SquareDto[8][8];

}
