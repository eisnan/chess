package chess.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PositionTest {

    @Test
    public void testComparableOrder() {
        Position position = Position.ofValid(File.a, Rank._1);
        Position position1= Position.ofValid(File.a, Rank._3);
        Position position2 = Position.ofValid(File.a, Rank._1);
        Position position3 = Position.ofValid(File.b, Rank._2);
        Position position4 = Position.ofValid(File.h, Rank._4);

        TreeSet<Position> positions = new TreeSet<>(Arrays.asList(position, position1, position2, position3, position4));
    }

    @Test
    public void testStaticFactoryMethods() {
        Optional<Position> position = Position.of(0, 0);

        System.out.println(position.get());

    }

    @Test
    public void testStaticFactoryMethodsInvalidPosition() {
        Optional<Position> position = Position.of(12, 10);

        assertFalse(position.isPresent());


    }
}