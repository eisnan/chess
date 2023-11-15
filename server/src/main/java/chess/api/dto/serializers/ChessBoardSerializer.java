package chess.api.dto.serializers;

import chess.api.dto.ChessBoardDto;
import chess.api.dto.SquareDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ChessBoardSerializer extends JsonSerializer<ChessBoardDto> {
    @Override
    public void serialize(ChessBoardDto chessBoard, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", chessBoard.getBoardId());
        gen.writeArrayFieldStart("model");
        for (SquareDto[] squareDto : chessBoard.getModel()) {
            gen.writeStartObject();
            gen.writeObject(squareDto);
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
