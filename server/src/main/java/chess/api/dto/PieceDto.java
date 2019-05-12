package chess.api.dto;

import lombok.Data;

/**
 * Created by Gabs on 5/12/2019.
 */
@Data
public class PieceDto {
    private ColorDto pieceColor;
    private PieceTypeDto pieceType;


    public String getNotation() {
        return this.getPieceType().name() + this.getPieceColor().name();
    }
}
