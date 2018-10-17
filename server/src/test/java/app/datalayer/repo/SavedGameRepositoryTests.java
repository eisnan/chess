package app.datalayer.repo;

import app.domain.SavedGame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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