package app.domain.moving;

import chess.domain.moving.AlgebraicNotationLoader;
import chess.domain.moving.PlayerMove;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

public class AlgebraicNotationLoaderTest {

    @Test
    public void loadFromFile() throws IOException {
        AlgebraicNotationLoader algebraicNotationLoader = new AlgebraicNotationLoader();

        String fileContents = Resources.toString(Resources.getResource("game2.pgn"), Charset.defaultCharset());


        algebraicNotationLoader.loadFrom(fileContents);

        System.out.println();

    }
}