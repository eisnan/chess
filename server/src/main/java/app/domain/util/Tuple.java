package app.domain.util;

import java.util.Objects;

public final class Tuple<LEFT, RIGHT> {
    private LEFT left;
    private RIGHT right;

    public Tuple(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    public static <LEFT, RIGHT> Tuple<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        return new Tuple<>(left, right);
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
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return Objects.equals(left, tuple.left) &&
                Objects.equals(right, tuple.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public String toString() {
        return "Tuple{" + "left=" + left + ", right=" + right + '}';
    }
}
