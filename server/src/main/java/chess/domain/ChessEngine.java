package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.validators.KValidator;
import com.google.common.eventbus.EventBus;

public class ChessEngine {

    private final ChessBoard chessBoard;
    private EventBus eventBus = new EventBus();
    private final KValidator kValidator = new KValidator();


    public ChessEngine(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        eventBus.register(kValidator);
    }

    public void selectPiece() {

    }

    public void move() {



    }

    public PieceColor getColorToMove() {
        PlayerMove lastMove = chessBoard.getLastMove();
        return lastMove != null ? lastMove.getPiece().getPieceColor() : PieceColor.WHITE;
    }

    public boolean checkMate() {
        return false;
    }
}
