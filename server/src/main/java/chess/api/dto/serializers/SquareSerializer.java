package chess.api.dto.serializers;

import chess.api.dto.SquareDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by Gabs on 5/12/2019.
 */
public class SquareSerializer extends JsonSerializer<SquareDto> {
    @Override
    public void serialize(SquareDto squareDto, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("col", squareDto.getSquareColor().name());
        gen.writeStringField("pos", squareDto.getPosition().getNotation());
        gen.writeStringField("pc", squareDto.getPiece() != null ? squareDto.getPiece().getNotation() : null);
        gen.writeEndObject();
    }
}
