package app.domain;

import app.domain.moving.MoveSettings;
import app.domain.moving.SpecialMove;
import app.domain.moving.moves.Move;
import app.domain.moving.rules.KingMovingRule;
import app.domain.moving.rules.MovingRule;
import app.domain.moving.rules.MovingRules;
import app.domain.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CheckRunner {

    private final ChessBoard chessBoard;
    private final PieceColor pieceColor;
    private final Tuple<Position, Piece> king;
    private Tuple<KingInCheck, Set<Position>> kingInCheckStrategy;

    public CheckRunner(ChessBoard chessBoard, PieceColor pieceColor) {
        this.chessBoard = chessBoard;
        this.pieceColor = pieceColor;
        this.king = chessBoard.getKing(pieceColor);
        this.kingInCheckStrategy = getStrategy(chessBoard, pieceColor);
    }

    private Tuple<KingInCheck, Set<Position>> getStrategy(ChessBoard chessBoard, PieceColor pieceColor) {

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);
        //isKingInCheck if king is protected by own piece
        Position kingPosition = king.getLeft();
        Piece kingPiece = king.getRight();

        Collection<Position> adjacentPositions = chessBoard.getAdjacentPositions(kingPosition);
        Predicate<Position> nullPositionOrOppositeColor = position -> chessBoard.getModel().get(position) == null ||
                chessBoard.getModel().get(position).getPieceColor() == pieceColor.oppositeColor();
        Set<Position> openPositions = adjacentPositions.stream().filter(nullPositionOrOppositeColor).collect(Collectors.toSet());

        if (openPositions.size() >= 4) {
            return Tuple.of(new UnprotectedKingInCheck(), openPositions);
        } else {
            return Tuple.of(new ProtectedKingInCheck(), openPositions);
        }
    }

    public boolean isKingInCheck() {

        Position kingPosition = king.getLeft();
        Piece kingPiece = king.getRight();

        boolean isKingInCheck = false;

        KingInCheck kingInCheck = kingInCheckStrategy.getLeft();
        Set<Position> openPositions = kingInCheckStrategy.getRight();

        System.out.println(openPositions);

        boolean knightsAttackKing = kingInCheckStrategy.getLeft().knightsAttackKing(chessBoard, pieceColor, kingPosition);



        // based on open positions determine open (vulnerable) vectors/directions
        Collection<Move> openDirections = new PositionInterpreter().getAttackDirections(chessBoard, pieceColor, kingPosition, openPositions);

        System.out.println(openDirections);


        // follow those directions, see if encounter enemy piece
        Map<Move, Integer> movingSettings = MovingRules.getMovingRule(kingPiece.getPieceType()).getMoveDescribers().stream()
                .filter(moveDescriber -> !(moveDescriber instanceof SpecialMove))
                .filter(openDirections::contains).collect(Collectors.toMap(moveDescriber -> moveDescriber, movingPositions -> 8));

        for (Move move : openDirections) {
            Collection<Position> positions = move.checkMove(chessBoard, new MoveSettings(kingPosition, kingPiece, new KingMovingRule(), movingSettings));
            System.out.println(positions);
            Optional<Tuple<Position, Piece>> firstPieceOnDirection = new PositionInterpreter().findFirstPieceOnDirection(chessBoard, move, positions);
            if (firstPieceOnDirection.isPresent() && kingPiece.getPieceColor().isOppositeColor(firstPieceOnDirection.get().getRight().getPieceColor())) {
                Collection<Position> attackingPositions = MovingRules.getMovingRule(firstPieceOnDirection.get().getRight().getPieceType()).getAttackingPositions(chessBoard, firstPieceOnDirection.get().getRight(), firstPieceOnDirection.get().getLeft());
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

//    private Collection<Piece> findAllPiecesWithAttackDirections(PieceColor pieceColor, Collection<Move> attackDirections) {
//
//        attackDirections.forEach(moveDescriber -> {
//            Collection<MovingRule> allMovingRules = MovingRules.getAllMovingRules();
//            allMovingRules.forEach(movingRule -> {
//                Map<PieceColor, Collection<Move>> capturingMoves = movingRule.getCaptureParameters();
//                Collection<Move> moveDescribers = capturingMoves.get(pieceColor);
//                if (moveDescribers.contains(moveDescriber)) {
//                    PieceType pieceType = movingRule.getPieceType();
//                    System.out.println(pieceType);
//                }
//
//            });
//
//        });
//
//        return null;
//    }


    public boolean isKingInCheck2(ChessBoard chessBoard, PieceColor pieceColor) {

        boolean isKingInCheck = false;

        Tuple<Position, Piece> king = chessBoard.getKing(pieceColor);


        return isKingInCheck;

    }

}
