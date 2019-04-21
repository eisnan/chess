package app.domain;

import app.datalayer.converters.JpaToStringConverter;
import app.datalayer.repo.MoveEntity;
import app.entity.Game;

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
    private Collection<MoveEntity> wMoveEntities;

    @Convert(converter = JpaToStringConverter.class)
    private Player bPlayer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<MoveEntity> bMoveEntities;

    public RunningGame() {
    }

    public RunningGame(Player wPlayer, Player bPlayer) {
        this.wPlayer = wPlayer;
        this.bPlayer = bPlayer;
        this.wMoveEntities = new LinkedList<>();
        this.bMoveEntities = new LinkedList<>();
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

    public boolean wMakeMove(MoveEntity moveEntity) {
        return wMoveEntities.add(moveEntity);
    }

    public boolean bMakeMove(MoveEntity moveEntity) {
        return bMoveEntities.add(moveEntity);
    }

    @Override
    public String toString() {
        return "RunningGame{" + "id=" + id + ", wPlayer=" + wPlayer + ", wMoveEntities=" + wMoveEntities + ", bPlayer=" + bPlayer + ", bMoveEntities=" + bMoveEntities + '}';
    }
}
