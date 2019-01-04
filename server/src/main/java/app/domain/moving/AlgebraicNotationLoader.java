package app.domain.moving;

import app.domain.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class AlgebraicNotationLoader {

    private ChessBoard chessBoard = new ChessBoard();

    public AlgebraicNotationLoader() {
        chessBoard.initModel();
        chessBoard.arrangePiecesForStart();
    }

    public Collection<Move> loadFrom(String algebraicNotation) {

        Collection<Move> loadedMoves = new ArrayList<>();

        String[] split = algebraicNotation.split("(\\d+\\.)");

        Stream.of(split).filter(s -> s.length() > 0).map(String::trim).forEach(s -> {
            String[] moves = s.split(" ");

            loadedMoves.add(parseMove(moves[0], PieceColor.WHITE));
            loadedMoves.add(parseMove(moves[1], PieceColor.BLACK));

        });


        return loadedMoves;
    }

    private Move parseMove(String move, PieceColor pieceColor) {

        PieceType pieceType = PieceType.getByNotationSymbol(move);
        Piece piece = new Piece(pieceColor, pieceType);

        Position toPosition = new Position(move);

        if (move.length() == 2) { //normal move
            //get all pieces of that type from chessboard

            // find out which could have moved to that position
        } else { //other move

        }
        return new Move(piece, null, toPosition);
    }

    public Collection<Move> loadFrom(File notationFile) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(notationFile));

        return loadFrom(reader.lines().findFirst().get());
    }

}
