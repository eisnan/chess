package app.datalayer.repo;

import app.domain.moving.MoveEntity;
import org.springframework.data.repository.CrudRepository;

public interface MoveRepository extends CrudRepository<MoveEntity, Long> {

}
