package app.domain;

import java.util.Optional;

public interface MoveDescriber {

    Optional<Position> checkMove(MoveSettings moveSettings);


}
