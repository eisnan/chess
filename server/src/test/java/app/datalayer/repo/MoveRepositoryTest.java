package app.datalayer.repo;

import app.datalayer.repo.MoveRepository;
import app.domain.File;
import app.domain.Move;
import app.domain.Piece;
import app.domain.Rank;
import app.domain.util.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MoveRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MoveRepository moveRepository;

    @Test
    public void testFindMove() {

        Move move = new Move();
        move.setPiece(Piece.PAWN);
        move.setCoordinateFrom(new Tuple<>(File.a, Rank._3));
        entityManager.persist(move);

        Iterable<Move> all = moveRepository.findAll();

        System.out.println(all);

    }
}
