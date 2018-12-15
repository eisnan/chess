package app.domain;

import app.datalayer.converters.JpaToStringConverter;
import app.domain.moving.Move;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class RunningGame extends Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = JpaToStringConverter.class)
    private Player wPlayer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Move> wMoves;

    @Convert(converter = JpaToStringConverter.class)
    private Player bPlayer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Move> bMoves;

    public RunningGame() {
    }

    public RunningGame(Player wPlayer, Player bPlayer) {
        this.wPlayer = wPlayer;
        this.bPlayer = bPlayer;
        this.wMoves = new LinkedList<>();
        this.bMoves = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getwPlayer() {
        return wPlayer;
    }

    public Player getbPlayer() {
        return bPlayer;
    }

    public boolean wMakeMove(Move move) {
        return wMoves.add(move);
    }

    public boolean bMakeMove(Move move) {
        return bMoves.add(move);
    }

    @Override
    public String toString() {
        return "RunningGame{" + "id=" + id + ", wPlayer=" + wPlayer + ", wMoves=" + wMoves + ", bPlayer=" + bPlayer + ", bMoves=" + bMoves + '}';
    }
}
