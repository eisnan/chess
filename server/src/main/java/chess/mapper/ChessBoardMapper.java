package chess.mapper;

import chess.api.dto.*;
import chess.domain.*;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface ChessBoardMapper {

    /**
     * Returns the board snapshot in a handwriting manner
     */
    default ChessBoardDto toDto(ChessBoard chessBoard) {
        Map<Position, Piece> model = chessBoard.getModel();
        ChessBoardDto chessBoardDto = new ChessBoardDto();
        int i = 0, j = 0;
        for (Map.Entry<Position, Piece> entry : model.entrySet()) {
            chessBoardDto.getModel()[i][j++] = new SquareDto(toDto(entry.getKey().getSquareColor()), toDto(entry.getKey()), toDto(entry.getValue()));
            if (j > 7) {
                j = 0;
                i++;
            }
        }

        return chessBoardDto;
    }

    ColorDto toDto(SquareColor squareColor);

    PositionDto toDto(Position position);

    PieceDto toDto(Piece piece);

    default ColorDto toDto(PieceColor pieceColor) {
        return ColorDto.valueOf(pieceColor.getColorNotation());
    }

    ;

    default PieceTypeDto toDto(PieceType pieceType) {
        return PieceTypeDto.valueOf(pieceType.getTypeNotation());
    }


}
