package app.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MoveDescriber {

    List<Position> checkMove(MoveSettings moveSettings);


}
