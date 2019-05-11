package chess.domain;

public enum File {
    a, b, c, d, e, f, g, h;

    public static boolean areAdjacent(File file1, File file2) {
        return Math.abs(file1.ordinal() - file2.ordinal()) == 1;
    }
}
