package core;

import org.springframework.data.repository.CrudRepository;

import java.util.Queue;

public interface MovesRepository extends CrudRepository<Moves, Long> {

//    Queue<Move> findByGame(Game game);
}
