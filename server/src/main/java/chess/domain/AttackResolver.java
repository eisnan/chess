package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.rules.MovingRule;
import chess.domain.moving.rules.MovingRules;
import chess.domain.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AttackResolver {


    public Collection<Pair<Position, Piece>> whoIsAttackingPosition(ChessBoard chessBoard, Position... positions) {
        return Stream.of(positions).map(position -> whoIsAttackingPosition(chessBoard, position) ).flatMap(Collection::stream).collect(Collectors.toList());
    }

    public Collection<Pair<Position, Piece>> whoIsAttackingPosition(ChessBoard chessBoard, Position position) {

        Piece piece = chessBoard.get(position);
        Collection<Pair<Position, Piece>> enemyPieces = chessBoard.q.getPieces(piece.getPieceColor().oppositeColor(), PieceType.values());


        List<PlayerMove> allAttacks = enemyPieces.stream().map(positionPiece -> {
            MovingRule movingRule = MovingRules.getMovingRule(positionPiece.getRight().getPieceType());
            return movingRule.getAttackingPositions(chessBoard, positionPiece.getRight(), positionPiece.getLeft());
        }).flatMap(Collection::stream).collect(Collectors.toList());

        boolean attacking = allAttacks.stream().map(PlayerMove::getToPosition).collect(Collectors.toList()).contains(position);

        return allAttacks.stream().map(playerMove -> Pair.of(playerMove.getToPosition(), playerMove.getPiece())).collect(Collectors.toList());
    }

}
