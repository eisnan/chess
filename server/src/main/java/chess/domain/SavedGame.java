package chess.domain;

import chess.entity.Game;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class SavedGame extends Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public SavedGame() {
    }

    public SavedGame(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavedGame savedGame = (SavedGame) o;
        return Objects.equals(name, savedGame.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "SavedGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
