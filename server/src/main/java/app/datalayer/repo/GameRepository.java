package app.datalayer.repo;

import app.domain.Game;
import app.domain.SavedGame;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository<T extends Game> extends CrudRepository<T, Long> {

//    Optional<SavedGame> findByName(String name);
}
