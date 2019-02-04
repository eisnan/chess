package app.domain.moving.moves;

import app.domain.ChessBoard;
import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerAction;
import app.domain.moving.PlayerMove;

import java.util.Set;

public interface Move {

    Set<PlayerMove> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);


}
