package core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovesRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovesRepository movesRepository;

    @Test
    public void testFindByGame() {

        UUID uuid = UUID.randomUUID();
        Game game = new Game(uuid);
        entityManager.persist(game);

        Moves moves = new Moves(game);
        entityManager.persist(moves);
        

        Iterable<Moves> all = movesRepository.findAll();

        System.out.println(all);

    }
}
