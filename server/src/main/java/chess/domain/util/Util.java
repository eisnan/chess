package chess.domain.util;

import java.util.Collection;

public class Util {

    /**
     * Does not care about collections containing duplicates
     */
    public static <T> boolean collectionsEqualIgnoreOrder(Collection<T> collection1, Collection<T> collection2) {
        return collection1.containsAll(collection2) && collection2.containsAll(collection1);
    }




}
