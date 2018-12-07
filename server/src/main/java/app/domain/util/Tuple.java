package app.domain.util;

public final class Tuple<LEFT, RIGHT> {
    private LEFT left;
    private RIGHT right;

    public Tuple(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    public LEFT getLeft() {
        return left;
    }

    public RIGHT getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Tuple{" + "left=" + left + ", right=" + right + '}';
    }
}
