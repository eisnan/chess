package app.domain;

import java.util.Optional;

public enum MoveType implements MoveDescriber {
    FORWARD_DIAGONAL_LEFT {
        @Override
        public Optional<Position> checkMove(MoveSettings moveSettings) {

            Position currentPosition = moveSettings.getCurrentPosition();
            switch (moveSettings.getPieceColor()) {


                case WHITE:
                    try {
                        return Optional.of(new Position(currentPosition.getFile().ordinal() - moveSettings.getSpaces(),
                                currentPosition.getRank().ordinal() + moveSettings.getSpaces()));
                    } catch (InvalidPositionException ex) {
                        System.out.println(ex);
                    }
                case BLACK:
                    break;
            }
            return Optional.empty();
        }


    }, FORWARD {
        @Override
        public Optional<Position> checkMove(MoveSettings moveSettings) {
            Position currentPosition = moveSettings.getCurrentPosition();
            switch (moveSettings.getPieceColor()) {


                case WHITE:
                    try {
                        return Optional.of(new Position(currentPosition.getFile().ordinal(),
                                currentPosition.getRank().ordinal() + moveSettings.getSpaces()));
                    } catch (InvalidPositionException ex) {
                        System.out.println(ex);
                    }
                case BLACK:
                    break;
            }
            return Optional.empty();
        }
    }, FORWARD_DIAGONAL_RIGHT {
        @Override
        public Optional<Position> checkMove(MoveSettings moveSettings) {
            Position currentPosition = moveSettings.getCurrentPosition();
            switch (moveSettings.getPieceColor()) {


                case WHITE:
                    try {
                        return Optional.of(new Position(currentPosition.getFile().ordinal() + moveSettings.getSpaces(),
                                currentPosition.getRank().ordinal() + moveSettings.getSpaces()));
                    } catch (InvalidPositionException ex) {
                        System.out.println(ex);
                    }
                case BLACK:
                    break;
            }
            return Optional.empty();
        }
    };


}