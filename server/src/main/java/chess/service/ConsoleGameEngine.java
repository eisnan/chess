package chess.service;

import chess.domain.moving.PlayerMove;
import chess.domain.*;

import java.util.Collection;
import java.util.stream.Collectors;

public class ConsoleGameEngine {

    public Collection<Position> getAvailableMoves(ChessBoard chessBoard, Piece piece, Position position) {
        //validate
//        validate(userInput);


        // map user input to piece

        Collection<PlayerMove> availablePositions = new PositionResolver().getValidMoves(chessBoard, position);
        return availablePositions.stream().map(playerMove -> playerMove.getToPosition()).collect(Collectors.toSet());

//        // map user input to move
//        new PlayerAction().move(chessBoard, piece, positionFrom, positionTo);

//        return true;
    }

    public Position getPosition(String positionLetter) {
        return new Position(positionLetter.charAt(0), Character.getNumericValue(positionLetter.charAt(1)));
    }

    private void validate(String userInput) {
        if (userInput.length() < 2) {
            throw new IllegalArgumentException();
        }
    }

    public Piece getPiece(String pieceLetter, String color) {
//        if (!Arrays.asList("WHITE", "BLACK").contains(color)) {
//            throw new IllegalArgumentException();
//        }


        return new Piece(PieceColor.valueOf(color), PieceType.getByNotationSymbol(pieceLetter));
    }

    public void printBoard(ChessBoard chessBoard) {
        Piece[][] arrayModel = chessBoard.q.getArrayModel();
        ConsolePrinter.print(arrayModel);
    }
}
