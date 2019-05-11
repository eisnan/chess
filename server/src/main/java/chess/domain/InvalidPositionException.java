package chess.domain;

import lombok.Getter;

@Getter
public class InvalidPositionException extends RuntimeException {

    private final int file;
    private int rank;

    public InvalidPositionException(String exMessage, int file, int rank) {
        super(exMessage);
        this.file = file;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "InvalidPositionException{" + "file=" + file + ", rank=" + rank + '}';
    }
}
