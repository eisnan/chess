package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.PlayerMover;

public class CheckEvaluator {


    public boolean isPinnedPiece(ChessBoard chessBoard, Position fromPosition) {

        if (chessBoard.get(fromPosition).getPieceType() == PieceType.KING) {
            return false;
        }

        // can't move piece if leaving your own king in check
        ChessBoard evaluate = chessBoard.evaluate();

        Piece piece = chessBoard.getModel().get(fromPosition);
        evaluate.getModel().put(fromPosition, null);

        CheckRunner checkRunner = new CheckRunner(evaluate, piece.getPieceColor());

        return checkRunner.isKingInCheck();
    }

    // when enemy piece attacks
    public boolean isCheckMove(ChessBoard chessBoard, PlayerMove playerMove) {

        ChessBoard evaluate = chessBoard.evaluate();

        Piece piece = playerMove.getPiece();
        PieceColor oppositeColor = piece.getPieceColor().oppositeColor();

        PlayerMover playerMover = new PlayerMover();

        playerMover.move(evaluate, playerMove);

        CheckRunner checkRunner = new CheckRunner(evaluate, oppositeColor);

        return checkRunner.isKingInCheck();
    }


    public boolean isCheckedMove(ChessBoard chessBoard, PlayerMove playerMove) {
        ChessBoard evaluate = chessBoard.evaluate();

        Piece piece = playerMove.getPiece();
        PieceColor oppositeColor = piece.getPieceColor();

        PlayerMover playerMover = new PlayerMover();

        playerMover.fMove(evaluate, playerMove.getFromPosition(), playerMove.getToPosition());

        CheckRunner checkRunner = new CheckRunner(evaluate, oppositeColor);

        return checkRunner.isKingInCheck();
    }




}
