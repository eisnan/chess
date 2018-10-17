package app.datalayer.converters;

import app.domain.Player;

import javax.persistence.AttributeConverter;

public class JpaToStringConverter implements AttributeConverter<Player, String> {
    @Override
    public String convertToDatabaseColumn(Player attribute) {
        return attribute.getName();
    }

    @Override
    public Player convertToEntityAttribute(String dbData) {
        return new Player(dbData);
    }
}
