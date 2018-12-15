package app.domain;

import org.junit.Test;

import static org.junit.Assert.*;

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