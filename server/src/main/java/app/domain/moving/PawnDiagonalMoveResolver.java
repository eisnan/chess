package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.validators.PawnValidator;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PawnDiagonalMoveResolver implements MoveResolver {


    @Override
    public Collection<Position> validate(ChessBoard chessBoard, MoveDescriber moveDescriber, MoveSettings moveSettings, PieceColor pieceColor, Collection<Position> positions) {
        Piece selectedPiece = moveSettings.getPiece();
        Position currentPosition = moveSettings.getCurrentPosition();
        return positions.stream().filter(position -> {
            Piece piece = chessBoard.getModel().get(position);
            return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : PawnValidator.isEnPassant(chessBoard, selectedPiece, currentPosition, position));
        }).collect(Collectors.toCollection(() -> new TreeSet<>(moveDescriber.getPositionComparator())));
    }
}
