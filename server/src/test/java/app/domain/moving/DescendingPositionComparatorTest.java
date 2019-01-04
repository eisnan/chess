package app.domain.moving;

import app.domain.File;
import app.domain.Position;
import app.domain.Rank;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class DescendingPositionComparatorTest {

    @Test
    public void compare() {

        DescendingPositionComparator comparator = new DescendingPositionComparator();

        Position position1 = new Position(File.a, Rank._5);
        Position position2 = new Position(File.g, Rank._7);
        Position position3 = new Position(File.h, Rank._1);
        Position position4 = new Position(File.d, Rank._3);
        Position position5 = new Position(File.d, Rank._2);
        Position position6 = new Position(File.a, Rank._2);
        Position position7 = new Position(File.g, Rank._8);
        Position position8 = new Position(File.a, Rank._6);

        Collection<Position> positions = new TreeSet<>(comparator);

        positions.addAll(Arrays.asList(position1, position2, position3, position4, position5, position6, position7, position8));

        System.out.println(positions);
    }
}