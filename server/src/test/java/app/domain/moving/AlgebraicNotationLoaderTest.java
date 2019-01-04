package app.domain.moving;

import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

public class AlgebraicNotationLoaderTest {

    @Test
    public void loadFromFile() throws IOException {
        AlgebraicNotationLoader algebraicNotationLoader = new AlgebraicNotationLoader();

        String fileContents = Resources.toString(Resources.getResource("game1.cn"), Charset.defaultCharset());


        Collection<Move> moves = algebraicNotationLoader.loadFrom(fileContents);

        System.out.println();

    }
}