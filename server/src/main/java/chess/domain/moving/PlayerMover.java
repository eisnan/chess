package chess.domain.moving;

import chess.domain.*;
import chess.domain.moving.validators.PawnValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerMover {

    private PawnPromoter pawnPromoter = new PawnPromoter();
    private PositionResolver resolver = new PositionResolver();

    public void move(ChessBoard chessBoard, Position fromPosition, Position toPosition) {

        Piece piece = chessBoard.get(fromPosition);

        //validate move
        List<Position> validPositions = resolver.getValidMoves(chessBoard, fromPosition).stream().map(PlayerMove::getToPosition).collect(Collectors.toList());
        if (!validPositions.contains(toPosition)) {
            throw new RuntimeException("Move is invalid");
        }

        // isKingInCheck for uncapturable pieces

        // isKingInCheck if king is left in isKingInCheck

        chessBoard.getModel().put(fromPosition, null);
        chessBoard.getModel().put(toPosition, piece);

        if (piece.getPieceType() == PieceType.PAWN) {
            boolean enPassant = new PawnValidator().isEnPassant(chessBoard, piece, fromPosition, toPosition);

            if (enPassant) {
                Position epCapture;
                if (piece.isWhitePiece()) {
                    // ofValid because pawn can never go backwards
                    epCapture = Position.ofValid(toPosition.getFile(), toPosition.getRank().previous());
                } else {
                    epCapture = Position.ofValid(toPosition.getFile(), toPosition.getRank().next());
                }
                chessBoard.getModel().put(epCapture, null);
            }

            if (piece.isWhitePiece() && toPosition.getRank() == Rank._8) {
//                pawnPromoter.promote(chessBoard, piece, promotedPiece);
            }
            if (piece.isBlackPiece() && toPosition.getRank() == Rank._1) {
//                pawnPromoter.promote(chessBoard, piece, promotedPiece);
            }
        }
        chessBoard.addMove(PlayerMove.of(piece, fromPosition, toPosition));

    }

    /**
     * Force move
     */
    public void fMove(ChessBoard chessBoard, Position fromPosition, Position toPosition) {
        Piece piece = chessBoard.get(fromPosition);
        chessBoard.getModel().put(fromPosition, null);
        chessBoard.getModel().put(toPosition, piece);
    }

    public void move(ChessBoard chessBoard, PlayerMove... playerMoves) {
        Stream.of(playerMoves).forEach(playerMove -> move(chessBoard, playerMove.getFromPosition(), playerMove.getToPosition()));
    }

}
