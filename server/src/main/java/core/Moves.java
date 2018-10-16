package core;

import javax.persistence.*;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Entity
public class Moves {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<Move> moves;

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

//    public Move newMove(Move move) {
//        moves.add(move);
//        return move;
//    }
//
//    public Collection<Move> getMoves() {
//        return moves;
//    }


    @Override
    public String toString() {
        return "Moves{" +
                "id=" + id +
                ", game=" + game +
                '}';
    }
}
