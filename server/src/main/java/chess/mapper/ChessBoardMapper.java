package chess.mapper;

import chess.api.dto.ChessBoardDto;
import chess.domain.ChessBoard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChessBoardMapper {

    ChessBoardDto toDto(ChessBoard car);

    ChessBoard toEntity(ChessBoardDto car);


}
