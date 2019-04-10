package app.domain;

import app.domain.moving.MoveSettings;
import app.domain.moving.PlayerMove;
import app.domain.moving.moves.IterableMove;
import app.domain.moving.moves.Move;
import app.domain.moving.moves.SpecialMove;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Gabs on 1/27/2019.
 */
public class ProtectedKingInCheck implements KingInCheck {
    @Override
    public boolean isKingInCheck(ChessBoard chessBoard, PieceColor pieceColor, Set<Position> openPositions) {

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);
        Position kingPosition = king.getLeft();
        Piece kingPiece = king.getRight();

        System.out.println(openPositions);

        boolean knightsAttackKing = knightsAttackKing(chessBoard, pieceColor, kingPosition);


        // based on open positions determine open (vulnerable) vectors/directions
        Collection<IterableMove> openDirections = new PositionInterpreter().getAttackDirections(chessBoard, pieceColor, kingPosition, openPositions);

        System.out.println(openDirections);


        // follow those directions, see if encounter enemy piece
        Map<Move, Integer> movingSettings = MovingRules.getMovingRule(kingPiece.getPieceType()).getMoveDescribers().stream()
                .filter(moveDescriber -> !(moveDescriber instanceof SpecialMove))
                .filter(openDirections::contains).collect(Collectors.toMap(moveDescriber -> moveDescriber, movingPositions -> 8));

        for (IterableMove move : openDirections) {
            Collection<PlayerMove> positions = move.checkMove(chessBoard, new MoveSettings(kingPosition, kingPiece, movingSettings));
            System.out.println(positions);
            Optional<Tuple<Position, Piece>> firstPieceOnDirection = new PositionInterpreter().findFirstPieceOnDirection(chessBoard, move, kingPiece, kingPosition);
            if (firstPieceOnDirection.isPresent() && kingPiece.getPieceColor().isOppositeColor(firstPieceOnDirection.get().getRight().getPieceColor())) {
                Collection<PlayerMove> attackingPositions = MovingRules.getMovingRule(firstPieceOnDirection.get().getRight().getPieceType()).getAttackingPositions(chessBoard, firstPieceOnDirection.get().getRight(), firstPieceOnDirection.get().getLeft());
                System.out.println(attackingPositions);
                if (attackingPositions.contains(kingPosition)) {
                    return true;
                }
            }
        }

        // look on these attack directions, see if any enemy piece is encountered and if it is, does it have this attack direction?

//        findAllPiecesWithAttackDirections(pieceColor, openDirections);

        return false;


    }
}
