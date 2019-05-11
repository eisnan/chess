package chess.domain.moving.moves;

import chess.domain.ChessBoard;
import chess.domain.moving.MoveSettings;
import chess.domain.moving.PlayerMove;

import java.util.Set;

public interface Move {

    Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);


}
