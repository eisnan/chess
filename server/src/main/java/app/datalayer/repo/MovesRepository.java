package app.datalayer.repo;

import app.domain.Moves;
import org.springframework.data.repository.CrudRepository;

public interface MovesRepository extends CrudRepository<Moves, Long> {

//    Queue<Move> findByGame(Game game);
}
