package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.Piece;
import app.domain.PieceColor;
import app.domain.Position;
import app.domain.moving.rules.PawnValidator;
import app.domain.moving.rules.SpecialMoveResolver;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnDiagonalMoveResolver implements SpecialMoveResolver {


    @Override
    public Collection<Position> validate(ChessBoard chessBoard, MoveSettings moveSettings, PieceColor pieceColor, Collection<Position> positions) {
        Piece selectedPiece = moveSettings.getPiece();
        Position currentPosition = moveSettings.getCurrentPosition();
        Set<Position> collected = positions.stream().filter(position -> {
            Piece piece = chessBoard.getModel().get(position);
            return (piece != null ? (selectedPiece.getPieceColor() != piece.getPieceColor()) : PawnValidator.isEnPassant(chessBoard, selectedPiece, currentPosition, position));
        }).collect(Collectors.toSet());
        return collected;
    }
}
