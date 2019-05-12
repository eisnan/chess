package chess.api.dto;

import lombok.Data;

/**
 * Created by Gabs on 5/12/2019.
 */
@Data
public class PositionDto {

    private FileDto file;
    private RankDto rank;

    public String getNotation() {
        return file.name() + rank.toString();
    }
}
