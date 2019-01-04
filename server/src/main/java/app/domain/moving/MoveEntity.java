package app.domain.moving;

import app.domain.File;
import app.domain.PieceType;
import app.domain.Rank;
import app.domain.util.Tuple;
import app.datalayer.converters.CoordinateConverter;

import javax.persistence.*;

@Entity
public class MoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PieceType pieceType;

    @Convert(converter = CoordinateConverter.class)
    private Tuple<File, Rank> coordinateFrom;

    @Transient
    private Tuple<File, Rank> disambiguatedMove;

    public MoveEntity() {
    }

    public MoveEntity(PieceType pieceType, Tuple<File, Rank> coordinateFrom) {
        this.pieceType = pieceType;
        this.coordinateFrom = coordinateFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public Tuple<File, Rank> getCoordinateFrom() {
        return coordinateFrom;
    }

    public void setCoordinateFrom(Tuple<File, Rank> coordinateFrom) {
        this.coordinateFrom = coordinateFrom;
    }

    @Override
    public String toString() {
        return "MoveEntity{" +
                "id=" + id +
                ", pieceType=" + pieceType +
                ", coordinateFrom=" + coordinateFrom +
                '}';
    }
}