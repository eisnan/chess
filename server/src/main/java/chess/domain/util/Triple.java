package chess.domain.util;

public class Triple<LEFT, MIDDLE, RIGHT> {

    private LEFT left;
    private MIDDLE middle;
    private RIGHT right;

    private Triple(LEFT left, MIDDLE middle, RIGHT right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public static <LEFT, MIDDLE, RIGHT> Triple<LEFT, MIDDLE, RIGHT> of(LEFT left, MIDDLE middle, RIGHT right) {
        return new Triple<>(left, middle, right);
    }

    public LEFT getLeft() {
        return left;
    }

    public MIDDLE getMiddle() {
        return middle;
    }

    public RIGHT getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Triple{" +
                "left=" + left +
                ", middle=" + middle +
                ", right=" + right +
                '}';
    }
}
