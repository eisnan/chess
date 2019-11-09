package chess.domain.start;

import chess.domain.Piece;
import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HardCodedPositionResolver implements StartPositionResolver {

    private PawnStartPositionResolver pawnStartPositionResolver = new PawnStartPositionResolver();
    private KingStartPositionResolver kingStartPositionResolver = new KingStartPositionResolver();
    private QueenStartPositionResolver queenStartPositionResolver = new QueenStartPositionResolver();
    private BishopStartPositionResolver bishopStartPositionResolver = new BishopStartPositionResolver();
    private KnightStartPositionResolver knightStartPositionResolver = new KnightStartPositionResolver();
    private RookStartPositionResolver rookStartPositionResolver = new RookStartPositionResolver();


    @Override
    public Map<Piece, Set<Position>> getInitialPositions() {
        Map<Piece, Set<Position>> initialPositions = new HashMap<>();

        //pawns
        Map<PieceColor, Set<Position>> pawnsPosition = pawnStartPositionResolver.getPawnsPosition();
        pawnsPosition.forEach((pieceColor, positions) -> initialPositions.put( Piece.of(pieceColor, PieceType.PAWN), positions));

        //kings
        Pair<Position, Position> kingsPosition = kingStartPositionResolver.getKingsPosition();
        initialPositions.put(Piece.getWhitePiece(PieceType.KING), Collections.singleton(kingsPosition.getLeft()));
        initialPositions.put(Piece.getBlackPiece(PieceType.KING), Collections.singleton(kingsPosition.getRight()));

        //queens
        Pair<Position, Position> queensPosition = queenStartPositionResolver.getQueensPosition();
        initialPositions.put(Piece.getWhitePiece(PieceType.QUEEN), Collections.singleton(queensPosition.getLeft()));
        initialPositions.put(Piece.getBlackPiece(PieceType.QUEEN), Collections.singleton(queensPosition.getRight()));

        //bishops
        Map<PieceColor, Set<Position>> bishopsPosition = bishopStartPositionResolver.getBishopsPosition();
        bishopsPosition.forEach((pieceColor, positions) -> initialPositions.put( Piece.of(pieceColor, PieceType.BISHOP), positions));

        //knights
        Map<PieceColor, Set<Position>> knightsPosition = knightStartPositionResolver.getKnightsPosition();
        knightsPosition.forEach((pieceColor, positions) -> initialPositions.put( Piece.of(pieceColor, PieceType.KNIGHT), positions));

        //rooks
        Map<PieceColor, Set<Position>> rooksPosition = rookStartPositionResolver.getRooksPosition();
        rooksPosition.forEach((pieceColor, positions) -> initialPositions.put( Piece.of(pieceColor, PieceType.ROOK), positions));

        return initialPositions;
    }


}
