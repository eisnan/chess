package chess.api.dto;

import chess.api.dto.serializers.ChessBoardSerializer;
import chess.api.dto.serializers.PositionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * Created by Gabs on 5/12/2019.
 */
@Data
@JsonSerialize(using = PositionSerializer.class)
public class PositionDto {

    private FileDto file;
    private RankDto rank;

    public String getNotation() {
        return file.name() + rank.getNotation();
    }
}
