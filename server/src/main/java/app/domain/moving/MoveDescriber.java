package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Position;

import java.util.List;

public interface MoveDescriber {

    List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings);


}
