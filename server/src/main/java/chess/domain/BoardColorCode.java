package chess.domain;

import static chess.domain.SquareColor.*;

/**
 * Created by Gabs on 5/12/2019.
 */
public class BoardColorCode {

    // it's 2D array upside down compared to the chessboard
    private static SquareColor[][] colorCodes = {
            {B, W, B, W, B, W, B, W},
            {W, B, W, B, W, B, W, B},
            {B, W, B, W, B, W, B, W},
            {W, B, W, B, W, B, W, B},
            {B, W, B, W, B, W, B, W},
            {W, B, W, B, W, B, W, B},
            {B, W, B, W, B, W, B, W},
            {W, B, W, B, W, B, W, B}
    };

    public static SquareColor get(int i, int j) {
        return colorCodes[i][j];
    }
}
