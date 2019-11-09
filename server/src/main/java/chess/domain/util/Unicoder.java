package chess.domain.util;

import chess.domain.Piece;

public class Unicoder {


    public static String getUnicode(Piece piece) {
        switch (piece.getPieceColor()) {
            case WHITE:
                switch (piece.getPieceType()) {
                    case PAWN:
                        return "&#9817;";
                    case KING:
                        return "&#9812;";
                    case QUEEN:
                        return "&#9813;";
                    case BISHOP:
                        return "&#9815;";
                    case KNIGHT:
                        return "&#9816;";
                    case ROOK:
                        return "&#9814;";
                }
                break;
            case BLACK:
                switch (piece.getPieceType()) {
                    case PAWN:
                        return "&#9823;";
                    case KING:
                        return "&#9818;";
                    case QUEEN:
                        return "&#9819;";
                    case BISHOP:
                        return "&#9821;";
                    case KNIGHT:
                        return "&#9822;";
                    case ROOK:
                        return "&#9820;";
                }
                break;
        }
        return "";
    }

}
