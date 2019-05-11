package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRule;
import chess.domain.moving.rules.MovingRules;
import chess.domain.util.Tuple;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Gabs on 1/27/2019.
 */
public interface KingInCheck {

    boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor, Set<Position> openPositions);

    default boolean knightsAttackKing(ChessBoard chessBoard, PieceColor pieceColor, Position kingPosition) {

        // isKingInCheck if there are enemy knights on the board
        Collection<Tuple<Position, Piece>> knights = chessBoard.getPieces(PieceType.KNIGHT, pieceColor.oppositeColor());
        //no vulnerable and no knights
        if (knights.isEmpty()) {
            return false;
        }

        // check if knights attack the king
        boolean knightsCheckKing = false;

        for (Tuple<Position, Piece> knight : knights) {
            MovingRule movingRule = MovingRules.getMovingRule(knight.getRight().getPieceType());
            Collection<PlayerMove> attackingPositionsOfKnight = movingRule.getAttackingPositions(chessBoard, knight.getRight(), knight.getLeft());

            knightsCheckKing |= attackingPositionsOfKnight.contains(kingPosition);
        }

        return knightsCheckKing;
    }
}
