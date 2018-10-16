package core;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SavedGameRepository extends CrudRepository<SavedGame, Long> {

    Optional<SavedGame> findByName(String name);

}
