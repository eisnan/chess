package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRule;
import chess.domain.moving.rules.MovingRules;
import chess.domain.util.Pair;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Gabs on 1/27/2019.
 */
public interface KingInCheck {

    boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor, Set<Position> openPositions);

    default boolean anyPieceAttackingPosition(ChessBoard chessBoard, Position kingPosition, Position newPosition) {



        return false;
    }

    default boolean knightsAttackKing(ChessBoard chessBoard, PieceColor pieceColor, Position kingPosition) {

        // isKingInCheck if there are enemy knights on the board
        Collection<Pair<Position, Piece>> knights = chessBoard.q.getPieces(PieceType.KNIGHT, pieceColor.oppositeColor());
        //no vulnerable and no knights
        if (knights.isEmpty()) {
            return false;
        }

        // check if knights attack the king
        boolean knightsCheckKing = false;

        for (Pair<Position, Piece> knight : knights) {
            MovingRule movingRule = MovingRules.getMovingRule(knight.getRight().getPieceType());
            Collection<PlayerMove> attackingPositionsOfKnight = movingRule.getAttackingPositions(chessBoard, knight.getRight(), knight.getLeft());

            knightsCheckKing |= attackingPositionsOfKnight.contains(kingPosition);
        }

        return knightsCheckKing;
    }
}
