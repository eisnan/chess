package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.PlayerMover;
import chess.domain.util.Pair;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckEvaluator {

    PositionResolver positionResolver = new PositionResolver();
    private AttackResolver attackResolver = new AttackResolver();

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

    public boolean isCheckMate(ChessBoard chessBoard, PieceColor pieceColor) {

        Pair<Position, Piece> king = chessBoard.q.getKing(pieceColor);

        // king is in check with nowhere to move
        CheckRunner checkRunner = new CheckRunner(chessBoard, pieceColor);
        boolean kingInCheck = checkRunner.isKingInCheck();

        if (!kingInCheck) {
            return false;
        }

        Collection<PlayerMove> validMoves = positionResolver.getValidMoves(chessBoard, king.getLeft());
        if (!validMoves.isEmpty()) {
            return false;
        }

        // there are no pieces of his color that can stop the check
        Collection<Pair<Position, Piece>> friendlyPieces = chessBoard.q.getPieces(pieceColor, PieceType.values());

        Collection<Pair<Position, Piece>> whoIsAttackingPosition = attackResolver.whoIsAttackingPosition(chessBoard, king.getRight().getPieceColor(), king.getLeft());

        Set<Position> friendlyPiecesPositions = friendlyPieces.stream().map(positionPiecePair -> positionPiecePair.getLeft()).collect(Collectors.toSet());

        Set<Position> whereFriendlyPiecesCanMove = friendlyPiecesPositions.stream()
                .map(position -> positionResolver.getValidMoves(chessBoard, position))
                .flatMap(Collection::stream).map(playerMove -> playerMove.getToPosition()).collect(Collectors.toSet());

        for (Pair<Position, Piece> attacker : whoIsAttackingPosition) {
            Set<Position> attackingPositions = positionResolver.getValidMoves(chessBoard, attacker.getLeft()).stream().map(PlayerMove::getToPosition).collect(Collectors.toSet());
            boolean containsAnyPieceThatCanDefend = attackingPositions.stream().anyMatch(whereFriendlyPiecesCanMove::contains);
            if (containsAnyPieceThatCanDefend) {
                return false;
            }


        }


        return false;
    }

    public boolean isStalemate(ChessBoard chessBoard, PieceColor pieceColor) {

        // king is not in check but all possible positions are checked

        // there are no more pieces of his color that can move

        return false;
    }


}
