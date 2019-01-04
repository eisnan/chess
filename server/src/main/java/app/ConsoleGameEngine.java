package app;

import app.domain.*;

import java.util.Collection;

public class ConsoleGameEngine {

    public Collection<Position> getAvailableMoves(ChessBoard chessBoard, Piece piece, Position position) {
        //validate
//        validate(userInput);


        // map user input to piece

        return new PositionResolver().getAvailablePositions(chessBoard, position);

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
        Piece[][] arrayModel = chessBoard.getArrayModel();
        ConsolePrinter.print(arrayModel);
    }
}
