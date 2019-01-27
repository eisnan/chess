package app;

import app.domain.*;
import app.domain.moving.PlayerAction;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleGameStarter {


    public static void main(String[] args) {

        ConsoleGameEngine engine = new ConsoleGameEngine();

        ChessBoard chessBoard = new ChessBoard();
        Piece[][] arrayModel = chessBoard.getArrayModel();
        ConsolePrinter.print(arrayModel);

        Scanner sc = new Scanner(System.in);
        int i = 0;
        String color;
        do {
            if (i % 2 == 0) {
                color = "WHITE";
            } else {
                color = "BLACK";
            }
            System.out.println(color + " choose piece:");

            String pieceSelection = sc.nextLine();

            if (pieceSelection.equals("exit")) {
                break;
            } else {

                String pieceLetter = pieceSelection.substring(0, 1);

                Piece selectedPiece = engine.getPiece(pieceLetter, color);

                String positionLetter;
                if (selectedPiece.getPieceType() != PieceType.PAWN) {
                    positionLetter = pieceSelection.substring(1);
                } else {
                    positionLetter = pieceSelection;
                }
                Position selectedPosition = engine.getPosition(positionLetter);

                Collection<Position> availableMoves = engine.getAvailableMoves(chessBoard, selectedPiece, selectedPosition);

                System.out.println("Your available moves are: ");
                String availableMovesOutput = availableMoves.stream().map(Position::getAlgebraicNotation).collect(Collectors.joining(", "));
                System.out.println(availableMovesOutput);

                String positionSelection = sc.nextLine();
                Position moveToPosition = engine.getPosition(positionSelection);

                new PlayerAction().move(chessBoard, selectedPiece, selectedPosition, moveToPosition);

//                boolean kingInCheck = new CheckRunner().isKingInCheck(chessBoard, selectedPiece.getPieceColor());
//                System.out.println(selectedPiece.getPieceColor() + " checks " + kingInCheck);




//                if (!successFullMove) {
//                    System.out.println("Invalid input, please try again");
//                    continue;
//                }

                i++;

                System.out.println();
                engine.printBoard(chessBoard);
                System.out.println("=======================");
                System.out.println();
            }
        } while (true);
        System.out.println("Game ended");
    }
}
