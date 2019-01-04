package app.datalayer.repo;

import app.domain.moving.Moves;
import org.springframework.data.repository.CrudRepository;

public interface MovesRepository extends CrudRepository<Moves, Long> {

//    Queue<MoveEntity> findByGame(Game game);
}
