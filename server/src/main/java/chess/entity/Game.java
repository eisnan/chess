package chess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_generator")
    @SequenceGenerator(name = "game_generator", sequenceName = "game_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "player1")
    @Transient
    private ChessUser player1;

    @Column(name = "player2")
    @Transient
    private ChessUser player2;

    @Transient
    private GameDetails gameDetails;

}
