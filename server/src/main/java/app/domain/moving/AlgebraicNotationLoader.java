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

    public Collection<PlayerMove> loadFrom(String algebraicNotation) {

        Collection<PlayerMove> loadedPlayerMoves = new ArrayList<>();

        String[] split = algebraicNotation.split("(\\d+\\.)");

        Stream.of(split).filter(s -> s.length() > 0).map(String::trim).forEach(s -> {
            String[] moves = s.split(" ");

            loadedPlayerMoves.add(parseMove(moves[0], PieceColor.WHITE));
            loadedPlayerMoves.add(parseMove(moves[1], PieceColor.BLACK));

        });


        return loadedPlayerMoves;
    }

    private PlayerMove parseMove(String move, PieceColor pieceColor) {

        PieceType pieceType = PieceType.getByNotationSymbol(move);
        Piece piece = new Piece(pieceColor, pieceType);

        Position toPosition = new Position(move);

        if (move.length() == 2) { //normal move
            //get all pieces of that type from chessboard

            // find out which could have moved to that position
        } else { //other move

        }
        return new PlayerMove(piece, null, toPosition);
    }

    public Collection<PlayerMove> loadFrom(File notationFile) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(notationFile));

        return loadFrom(reader.lines().findFirst().get());
    }

}
