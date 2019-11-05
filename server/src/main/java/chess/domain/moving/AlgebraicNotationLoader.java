package chess.domain.moving;

import chess.domain.*;
import chess.domain.util.ChessboardSingleton;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AlgebraicNotationLoader {

    private PositionResolver positionResolver = new PositionResolver();
    private PlayerMover mover = new PlayerMover();
    private Map<UUID, ChessBoard> gameMemory = ChessboardSingleton.INSTANCE.getGameMemory();


    public AlgebraicNotationLoader() {

    }

    public ChessBoard loadFrom(String algebraicNotation) {

        ChessBoard chessBoard = new ChessBoard();


        String[] split = algebraicNotation.split("(\\d+\\.)");

        Stream.of(split).filter(s -> s.length() > 0).map(String::trim).forEach(s -> {
            String[] moves = s.split(" ");

            Collection<PlayerMove> whiteMoves = parseMove(chessBoard, moves[0], PieceColor.WHITE);
            whiteMoves.forEach(mv -> mover.move(chessBoard, mv));
//            mover.move(chessBoard, whiteMoves);
            Collection<PlayerMove> blackMoves = parseMove(chessBoard, moves[1], PieceColor.BLACK);
            blackMoves.forEach(mv -> mover.move(chessBoard, mv));
//            mover.move(chessBoard, blackMoves);

        });


        return chessBoard;
    }

    private Collection<PlayerMove> parseMove(ChessBoard chessBoard, String moveNotation, PieceColor pieceColor) {
        Collection<MoveType> moveTypes = detectMoveType(moveNotation);

        return processMove(chessBoard, moveNotation, pieceColor, moveTypes);

//
//        Piece piece = new Piece(pieceColor, pieceType);
//        Position toPosition = new Position(positionNotation);
//        PlayerMove playerMove;
//        if (positionNotation.length() == 2) { //normal moveNotation
//
//
//        } else { //other moveNotation
//            playerMove = null;
//        }
//        return new PlayerMove(piece, playerMove.getFromPosition(), toPosition);
    }

//    private void processMove2(String moveNotation, Collection<MoveType> moveTypes) {
//        PieceType pieceType = detectPieceType(moveNotation);
////        processMove(moveNotation, moveTypes);
//
//        if (pieceType == PieceType.PAWN) {
//            positionNotation = moveNotation;
//        } else if (moveNotation.length() == 3) {
//            String pieceTypeNotation = moveNotation.substring(0, 1);
//            positionNotation = moveNotation.substring(1, 3);
//            pieceType = PieceType.getByNotationSymbol(pieceTypeNotation);
//        } else if (moveNotation.length() == 4) {
//            String pieceTypeNotation = moveNotation.substring(0, 1);
//
//            pieceType = PieceType.getByNotationSymbol(pieceTypeNotation);
//        } else {
//            throw new RuntimeException();
//        }
//
//
//        if (moveTypes.contains(MoveType.MOVE)) {
//
//        }
//    }


    private Collection<PlayerMove> processMove(ChessBoard chessBoard, String moveNotation, PieceColor pieceColor, Collection<MoveType> moveTypes) {
        if (moveTypes.containsAll(Arrays.asList(MoveType.PROMOTION, MoveType.CHECK))) {
            moveTypes.remove(MoveType.CHECK);
        }
        for (MoveType moveType : moveTypes) {
            return analyzeMove(chessBoard, moveNotation, pieceColor, moveType);
        }
        return null;
    }

    private Collection<PlayerMove> analyzeMove(ChessBoard chessBoard, String moveNotation, PieceColor pieceColor, MoveType moveType) {


        if (MoveType.isCastling(moveType)) {
            return castling(pieceColor, moveType);
        }

        PieceType pieceType = detectPieceType(moveNotation);
        Piece piece = new Piece(pieceColor, pieceType);

        if (moveType == MoveType.PROMOTION) {
            piece = new Piece(pieceColor, PieceType.PAWN);
        }

        Position toPosition;
        String lastChar = moveNotation.substring(moveNotation.length() - 1);
        if (lastChar.equals("+") && moveNotation.contains("=")) { //promotion and check
            toPosition = new Position(moveNotation.substring(0, 2));
        } else if (lastChar.equals("+")) { //check
            toPosition = new Position(moveNotation.substring(moveNotation.length() - 3, moveNotation.length() - 1)); //not sure for promoted panws
        } else {
            toPosition = new Position(moveNotation.substring(moveNotation.length() - 2)); //not sure for promoted panws
        }

        //get all pieces of that type from chessboard
        List<Position> positions = chessBoard.get(piece);

        // find out which could have moved to that position
        Collection<PlayerMove> availableMoves = new ArrayList<>();
        positions.stream().forEach(position -> {
            availableMoves.addAll(positionResolver.getAvailableMoves(chessBoard, position));
        });

        List<PlayerMove> playerMoves = availableMoves.stream().filter(pmove -> pmove.getToPosition().equals(toPosition)).collect(Collectors.toList());

        if (playerMoves.size() < 1) {
            throw new RuntimeException();
        } else if (playerMoves.size() > 1) {
            playerMoves = availableMoves.stream().filter(pmove -> pmove.getToPosition().equals(toPosition))
                    .filter(pmove -> pmove.getFromPosition().getFile().name().equals(moveNotation.substring(1, 2)))
                    .collect(Collectors.toList());
        }

        System.out.println(playerMoves);
        return playerMoves;


    }

    private Collection<PlayerMove> castling(PieceColor pieceColor, MoveType moveType) {

        Collection<PlayerMove> moves = new ArrayList<>();

        switch (pieceColor) {
            case WHITE:
                switch (moveType) {
                    case QUEEN_SIDE_CASTLING:
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.KING), new Position("e1"), new Position("c1")));
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.ROOK), new Position("a1"), new Position("d1")));
                        break;
                    case KING_SIDE_CASTLING:
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.KING), new Position("e1"), new Position("g1")));
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.ROOK), new Position("h1"), new Position("f1")));
                        break;
                }


                break;
            case BLACK:
                switch (moveType) {
                    case QUEEN_SIDE_CASTLING:
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.KING), new Position("e8"), new Position("c8")));
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.ROOK), new Position("a8"), new Position("d8")));
                        break;
                    case KING_SIDE_CASTLING:
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.KING), new Position("e8"), new Position("c8")));
                        moves.add(new PlayerMove(Piece.getWhitePiece(PieceType.ROOK), new Position("a8"), new Position("d8")));
                        break;
                }
                break;
        }

        return moves;
    }

    private PieceType detectPieceType(String moveNotation) {
        if (moveNotation.contains("=")) { // if piece is promoted then the real type if after the = sign
            String lastChar = moveNotation.substring(moveNotation.length() - 1);
            if (lastChar.equals("+")) {
                return PieceType.getByNotationSymbol(moveNotation.split("=")[1].substring(0, 1));
            } else {
                return PieceType.getByNotationSymbol(moveNotation.split("=")[1]);
            }

        }
        return PieceType.getByNotationSymbol(moveNotation.substring(0, 1));
    }

    private Collection<MoveType> detectMoveType(String moveNotation) {
        Collection<MoveType> moveTypes = new ArrayList<>();
        if (moveNotation.contains("+")) {
            moveTypes.add(MoveType.CHECK);
        }
        if (moveNotation.contains("x")) {
            moveTypes.add(MoveType.CAPTURE);
        }
        if (moveNotation.contains("=")) {
            moveTypes.add(MoveType.PROMOTION);
        }
        if (moveNotation.contains("O") || moveNotation.contains("0")) {
            if (moveNotation.equals("O-O-O") || moveNotation.contains("0-0-0")) {
                moveTypes.add(MoveType.QUEEN_SIDE_CASTLING);
            } else if (moveNotation.equals("O-O") || moveNotation.equals("0-0")) {
                moveTypes.add(MoveType.KING_SIDE_CASTLING);
            }
        }

        if (moveTypes.isEmpty()) {
            moveTypes.add(MoveType.MOVE);
        }
        return moveTypes;
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public ChessBoard loadFromFile(String file) throws IOException {

        String fileContents = Resources.toString(Resources.getResource(file), Charset.defaultCharset());

        return loadFrom(fileContents);

    }
}
