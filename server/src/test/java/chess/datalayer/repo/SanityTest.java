//package app.datalayer.repo;
//
//import app.domain.*;
//import Tuple;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest
//public class SanityTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
////    @Autowired
////    private GameRepository<RunningGame> runningGameGameRepository;
////
////    private GameRepository<SavedGame> savedGameGameRepository;
//
//    @Test
//    public void test() {
//        runningGameGameRepository.deleteAll();
//
//        RunningGame game = new RunningGame(new Player("Gabs"), new Player("L"));
//        game.wMakeMove(new MoveEntity(PieceType.PAWN, new Tuple<>(File.a, Rank._3)));
//
//        entityManager.persist(game);
//
//        game.bMakeMove(new MoveEntity(PieceType.PAWN, new Tuple<>(File.a, Rank._6)));
//
//        entityManager.persist(game);
//
//        Optional<RunningGame> byId = runningGameGameRepository.findById(game.getId());
//
//        System.out.println(byId.get());
//
//    }
//
//
//}
