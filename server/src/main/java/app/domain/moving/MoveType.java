package app.domain.moving;

import app.domain.InvalidPositionException;
import app.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum MoveType implements MoveDescriber {
    FORWARD_DIAGONAL_LEFT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            List<Position> possiblePositions = new ArrayList<>();
            List<Position> validPositions = new ArrayList<>();

            Position currentPosition = moveSettings.getCurrentPosition();
            Integer limit = moveSettings.getMaxLimit().get(this);
            switch (moveSettings.getPiece().getPieceColor()) {
                case WHITE:
                    Stream.iterate(1, x -> x + 1)
                            .limit(limit)
                            .forEach(integer -> {
                                try {
                                    Position position = new Position(currentPosition.getFile().ordinal() - integer,
                                            currentPosition.getRank().ordinal() + integer);
                                    possiblePositions.add(position);
                                } catch (InvalidPositionException ex) {
                                    System.out.println(ex);
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
                case BLACK:
                    Stream.iterate(1, x -> x + 1)
                            .limit(limit)
                            .forEach(integer -> {
                                try {
                                    Position position = new Position(currentPosition.getFile().ordinal() + integer,
                                            currentPosition.getRank().ordinal() - integer);
                                    possiblePositions.add(position);
                                } catch (InvalidPositionException ex) {
                                    System.out.println(ex);
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
            }
            return validPositions;
        }


    },
    FORWARD_DIAGONAL_RIGHT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            List<Position> possiblePositions = new ArrayList<>();
            List<Position> validPositions = new ArrayList<>();
            Position currentPosition = moveSettings.getCurrentPosition();
            Integer limit = moveSettings.getMaxLimit().get(this);
            switch (moveSettings.getPiece().getPieceColor()) {
                case WHITE:
                    Stream.iterate(1, x -> x + 1)
                            .limit(limit)
                            .forEach(integer -> {
                                try {
                                    Position position = new Position(currentPosition.getFile().ordinal() + integer,
                                            currentPosition.getRank().ordinal() + integer);


                                    possiblePositions.add(position);
                                } catch (InvalidPositionException ex) {
                                    System.out.println(ex);
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
                case BLACK:
                    Stream.iterate(1, x -> x + 1)
                            .limit(limit)
                            .forEach(integer -> {
                                try {
                                    Position position = new Position(currentPosition.getFile().ordinal() - integer,
                                            currentPosition.getRank().ordinal() - integer);


                                    possiblePositions.add(position);
                                } catch (InvalidPositionException ex) {
                                    System.out.println(ex);
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
            }
            return validPositions;
        }
    },
    FORWARD {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            List<Position> possiblePositions = new ArrayList<>();
            List<Position> validPositions = new ArrayList<>();
            Position currentPosition = moveSettings.getCurrentPosition();
            Integer limit = moveSettings.getMaxLimit().get(this);
            switch (moveSettings.getPiece().getPieceColor()) {
                case WHITE:
                    Stream.iterate(1, x -> x + 1)
                            .limit(limit)
                            .forEach(integer -> {
                                try {
                                    Position position = new Position(currentPosition.getFile().ordinal(),
                                            currentPosition.getRank().ordinal() + integer);
                                    possiblePositions.add(position);
                                } catch (InvalidPositionException ex) {
                                    System.out.println(ex);
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
                case BLACK:
                    Stream.iterate(1, x -> x + 1)
                            .limit(limit)
                            .forEach(integer -> {
                                try {
                                    Position position = new Position(currentPosition.getFile().ordinal(),
                                            currentPosition.getRank().ordinal() - integer);
                                    possiblePositions.add(position);
                                } catch (InvalidPositionException ex) {
                                    System.out.println(ex);
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
            }
            return validPositions;
        }
    },
    BACKWARD_DIAGONAL_LEFT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            return null;
        }
    },
    BACKWARD_DIAGONAL_RIGHT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            return null;
        }
    },
    BACKWARD {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            return null;
        }
    },
    LEFT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            return null;
        }
    },
    RIGHT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            return null;
        }
    },
    KNIGHT {
        @Override
        public List<Position> checkMove(MoveSettings moveSettings) {
            return null;
        }
    }



}