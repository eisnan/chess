package chess.api.dto;

/**
 * Created by Gabs on 5/12/2019.
 */
public enum RankDto {
    _1, _2, _3, _4, _5, _6, _7, _8;

    @Override
    public String toString() {
        return this.name().substring(1,2);
    }
}
