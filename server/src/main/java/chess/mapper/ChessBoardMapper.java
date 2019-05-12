package chess.mapper;

import chess.api.dto.*;
import chess.domain.*;
import chess.domain.util.Tuple;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface ChessBoardMapper {

    default ChessBoardDto toDto(ChessBoard chessBoard) {
        Map<Position, Piece> model = chessBoard.getModel();
        ChessBoardDto chessBoardDto = new ChessBoardDto();
        model.forEach((position, piece) -> chessBoardDto.getModel().put(toDto(position), toDto(piece)));
        return chessBoardDto;
    };

    PositionDto toDto(Position position);

    PieceDto toDto(Piece piece);

    default PieceColorDto toDto(PieceColor pieceColor) {
        return PieceColorDto.valueOf(pieceColor.getColorNotation());
    };

    default PieceTypeDto toDto (PieceType pieceType) {
        return PieceTypeDto.valueOf(pieceType.getTypeNotation());
    }



}
