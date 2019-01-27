package app.datalayer.repo;

import app.domain.Game;

import javax.persistence.*;

@Entity
public class Moves {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<MoveEntity> moves;

    public Moves() {
    }

    public Moves(Game game) {
        this.game = game;
//        this.moves = new ConcurrentLinkedDeque<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

//    public MoveEntity newMove(MoveEntity checkMove) {
//        moves.add(checkMove);
//        return checkMove;
//    }
//
//    public Collection<MoveEntity> getMoves() {
//        return moves;
//    }


    @Override
    public String toString() {
        return "Moves{" + "id=" + id + ", game=" + game + '}';
    }
}
