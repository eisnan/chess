package chess.datalayer.converters;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.util.Pair;
import jakarta.persistence.AttributeConverter;


public class CoordinateConverter implements AttributeConverter<Pair<File, Rank>, String> {
    @Override
    public String convertToDatabaseColumn(Pair<File, Rank> attribute) {
        File file = attribute.getLeft();
        Rank rank = attribute.getRight();
        return file.name() + "" + rank.getCoordinate();
    }

    @Override
    public Pair<File, Rank> convertToEntityAttribute(String dbData) {
        return new Pair<>(File.valueOf("" + dbData.charAt(0)), Rank.getRank("" + dbData.charAt(1)));
    }




}
