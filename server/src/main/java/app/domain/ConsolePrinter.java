package app.domain;

public class ConsolePrinter {


    public static void print(Piece[][] arrayModel) {

        for (int i = 0; i < arrayModel.length; i++) {
            for (int j = 0; j < arrayModel[i].length; j++) {
                Piece piece = arrayModel[i][j];
                if (piece != null) {
                    System.out.print(piece.getNotation());
                } else {
                    System.out.print("  ");
                }
                if (j != arrayModel[i].length-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }
}
