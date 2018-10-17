package app.datalayer.converters;

import app.domain.File;
import app.domain.Rank;
import app.domain.util.Tuple;

import javax.persistence.AttributeConverter;

public class CoordinateConverter implements AttributeConverter<Tuple<File, Rank>, String> {
    @Override
    public String convertToDatabaseColumn(Tuple<File, Rank> attribute) {
        File file = attribute.getLeft();
        Rank rank = attribute.getRight();
        return file.name() + "" + rank.getCoordinate();
    }

    @Override
    public Tuple<File, Rank> convertToEntityAttribute(String dbData) {
        return new Tuple<>(File.valueOf("" + dbData.charAt(0)), Rank.getEnum("" + dbData.charAt(1)));
    }




}
