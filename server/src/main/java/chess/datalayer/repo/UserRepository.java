package chess.datalayer.repo;

import chess.entity.ChessUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Gabs on 4/10/2019.
 */
public interface UserRepository extends JpaRepository<ChessUser, Long>{
}
