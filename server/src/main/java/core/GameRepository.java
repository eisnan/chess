package core;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
    Game findByUuid(UUID uuid);
}
