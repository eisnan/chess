package chess.api.dto.serializers;

import chess.domain.Piece;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PieceSerializer extends JsonSerializer<Piece> {
    @Override
    public void serialize(Piece piece, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("zxc", piece.getPieceColor().name() + piece.getPieceType().name());
        gen.writeEndObject();
    }
}
