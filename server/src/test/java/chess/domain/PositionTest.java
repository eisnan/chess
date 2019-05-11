package chess.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

public class PositionTest {

    @Test
    public void testComparableOrder() {
        Position position = new Position(File.a, Rank._1);
        Position position1= new Position(File.a, Rank._3);
        Position position2 = new Position(File.a, Rank._1);
        Position position3 = new Position(File.b, Rank._2);
        Position position4 = new Position(File.h, Rank._4);

        TreeSet<Position> positions = new TreeSet<>(Arrays.asList(position, position1, position2, position3, position4));
    }
}