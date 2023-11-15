package chess.datalayer.repo;

import chess.domain.File;
import chess.domain.PieceType;
import chess.domain.Rank;
import chess.datalayer.converters.CoordinateConverter;
import chess.domain.util.Pair;
import jakarta.persistence.*;

@Entity
public class MoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PieceType pieceType;

    @Convert(converter = CoordinateConverter.class)
    private Pair<File, Rank> coordinateFrom;

    @Transient
    private Pair<File, Rank> disambiguatedMove;

    public MoveEntity() {
    }

    public MoveEntity(PieceType pieceType, Pair<File, Rank> coordinateFrom) {
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

    public Pair<File, Rank> getCoordinateFrom() {
        return coordinateFrom;
    }

    public void setCoordinateFrom(Pair<File, Rank> coordinateFrom) {
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
