package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRule;
import chess.domain.util.Pair;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AttackResolver {


    public Collection<Pair<Position, Piece>> whoIsAttackingPosition(ChessBoard chessBoard, PieceColor pieceColor, Position... positions) {
        return Stream.of(positions).map(position -> whoIsAttackingPosition(chessBoard, pieceColor, position) ).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public Collection<Pair<Position, Piece>> whoIsAttackingPosition(ChessBoard chessBoard, PieceColor pieceColor, Position position) {

        Collection<Pair<Position, Piece>> enemyPieces = chessBoard.q.getPieces(pieceColor.oppositeColor(), PieceType.values());

        List<PlayerMove> allAttacks = enemyPieces.stream().map(positionPiece -> {
            MovingRule movingRule = positionPiece.getRight().getPieceType().getMovingRule();
            return movingRule.getAttackingPositions(chessBoard, positionPiece.getRight(), positionPiece.getLeft());
        }).flatMap(Collection::stream).collect(Collectors.toList());

        boolean attacking = allAttacks.stream().map(PlayerMove::getToPosition).collect(Collectors.toList()).contains(position);

        if (attacking) {
            return allAttacks.stream()
                    .map(playerMove -> Pair.of(playerMove.getToPosition(), playerMove.getPiece()))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
