package chess.api.dto;

import chess.api.dto.serializers.PieceSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * Created by Gabs on 5/12/2019.
 */
@Data
@JsonSerialize(using = PieceSerializer.class)
public class PieceDto {
    private PieceColorDto pieceColor;
    private PieceTypeDto pieceType;


    public String getNotation() {
        return this.getPieceColor().name() + this.getPieceType().name();
    }
}
