package chess.domain;

import chess.domain.moving.MoveType;
import chess.domain.moving.PlayerMove;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MoveTypeResolver {

    private CheckEvaluator checkEvaluator = new CheckEvaluator();

    public Collection<PlayerMove> update(ChessBoard chessBoard, Collection<PlayerMove> possibleMoves) {

        // promotion
        Set<PlayerMove> promotionMoves = possibleMoves.stream().map(playerMove -> {
            boolean promotionRank = playerMove.getPiece().getPieceColor().isPromotionRank(playerMove.getToPosition().getRank());
            if (promotionRank) {
                return PlayerMove.of(playerMove, MoveType.PROMOTION);
            } else {
                return playerMove;
            }
        }).collect(Collectors.toSet());

        Set<PlayerMove> validMovesUpdated = promotionMoves.stream().map(playerMove -> {
            if (checkEvaluator.isCheckMove(chessBoard, playerMove)) {
                return PlayerMove.of(playerMove, MoveType.CHECK);
            } else {
                return playerMove;
            }
        }).collect(Collectors.toSet());




        return validMovesUpdated;
    }



}
