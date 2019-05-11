package chess.datalayer.repo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SavedGameRepositoryTests {
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private SavedGameRepository savedGameRepository;
//
//    @Test
//    public void testFindSavedGameByName() {
//        SavedGame savedGame = new SavedGame("TestSavedGame");
//        entityManager.persist(savedGame);
//
//        Optional<SavedGame> persistedSavedGame = savedGameRepository.findByName(savedGame.getName());
//
//        assertThat(persistedSavedGame).hasValue(savedGame);
//    }
}