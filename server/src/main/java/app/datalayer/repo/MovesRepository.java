package app.datalayer.repo;

import org.springframework.data.repository.CrudRepository;

public interface MovesRepository extends CrudRepository<Moves, Long> {

//    Queue<MoveEntity> findByGame(Game game);
}
