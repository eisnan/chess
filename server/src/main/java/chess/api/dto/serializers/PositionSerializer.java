package chess.api.dto.serializers;

import chess.api.dto.PositionDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by Gabs on 5/12/2019.
 */
public class PositionSerializer extends JsonSerializer<PositionDto> {
    @Override
    public void serialize(PositionDto positionDto, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeString(positionDto.getFile().name() + positionDto.getRank().getNotation());
        gen.writeEndObject();
    }
}
