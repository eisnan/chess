package app.datalayer.repo;

import app.domain.moving.Move;
import org.springframework.data.repository.CrudRepository;

public interface MoveRepository extends CrudRepository<Move, Long> {

}
