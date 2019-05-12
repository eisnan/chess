package chess.api.dto.serializers;

import chess.api.dto.ChessBoardDto;
import chess.api.dto.PieceDto;
import chess.api.dto.PositionDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

public class ChessBoardSerializer extends JsonSerializer<ChessBoardDto> {
    @Override
    public void serialize(ChessBoardDto chessBoard, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", chessBoard.getBoardId());
        gen.writeArrayFieldStart("model");
        for (Map.Entry<PositionDto, PieceDto> entry : chessBoard.getModel().entrySet()) {
            gen.writeStartObject();
            gen.writeObjectField(entry.getKey().getNotation(), entry.getValue() != null ? entry.getValue().getNotation() : null);
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
