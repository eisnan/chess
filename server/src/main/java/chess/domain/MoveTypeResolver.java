package chess.domain;

import chess.domain.moving.PlayerMove;

import java.util.Collection;

public class MoveTypeResolver {

    public Collection<PlayerMove> update(ChessBoard chessBoard, Collection<PlayerMove> possiblePositions) {

        return possiblePositions;
    }
}
