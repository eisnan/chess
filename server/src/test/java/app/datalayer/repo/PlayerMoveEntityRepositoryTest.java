package app.datalayer.repo;

import app.domain.File;
import app.domain.PieceType;
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
public class PlayerMoveEntityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MoveRepository moveRepository;

    @Test
    public void testFindMove() {

        MoveEntity moveEntity = new MoveEntity();
        moveEntity.setPieceType(PieceType.PAWN);
        moveEntity.setCoordinateFrom(new Tuple<>(File.a, Rank._3));
        entityManager.persist(moveEntity);

        Iterable<MoveEntity> all = moveRepository.findAll();

        System.out.println(all);

    }
}
