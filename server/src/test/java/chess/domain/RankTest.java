package chess.domain;

import org.junit.Test;

public class RankTest {

    @Test
    public void next() {
        Rank rank = Rank._8;
        System.out.println(rank.next());
    }

    @Test
    public void previous() {

        Rank rank = Rank._1;
        System.out.println(rank.previous());
    }
}