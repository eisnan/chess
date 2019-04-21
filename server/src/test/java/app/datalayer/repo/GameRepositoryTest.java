package app.datalayer.repo;

import app.entity.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

//    @Autowired
//    private GameRepository gameRepository;

    @Test
    public void testFindByUUID() {
        UUID uuid = UUID.randomUUID();
        Game game = new Game();

        entityManager.persist(game);

//        Game foundGame = gameRepository.findByUuid(uuid);
//
//        assertEquals(uuid, foundGame.getUuid());
    }
}
