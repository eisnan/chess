package chess.api.dto.serializers;

import chess.api.dto.PieceDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PieceSerializer extends JsonSerializer<PieceDto> {
    @Override
    public void serialize(PieceDto pieceDto, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeString(pieceDto.getPieceColor().name() + pieceDto.getPieceType().name());
        gen.writeEndObject();
    }
}
