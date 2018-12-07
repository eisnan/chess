package app.domain;

import app.domain.util.Tuple;
import app.datalayer.converters.CoordinateConverter;

import javax.persistence.*;

@Entity
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PieceType pieceType;

    @Convert(converter = CoordinateConverter.class)
    private Tuple<File, Rank> coordinateFrom;

    @Transient
    private Tuple<File, Rank> disambiguatedMove;

    public Move() {
    }

    public Move(PieceType pieceType, Tuple<File, Rank> coordinateFrom) {
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
        return "Move{" +
                "id=" + id +
                ", pieceType=" + pieceType +
                ", coordinateFrom=" + coordinateFrom +
                '}';
    }
}
