package chess.domain.util;

import java.util.Objects;

public final class Pair<LEFT, RIGHT> {
    private LEFT left;
    private RIGHT right;

    public Pair(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    public static <LEFT, RIGHT> Pair<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        return new Pair<>(left, right);
    }

    public LEFT getLeft() {
        return left;
    }

    public RIGHT getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(left, pair.left) &&
                Objects.equals(right, pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return left + "," + right;
    }
}
