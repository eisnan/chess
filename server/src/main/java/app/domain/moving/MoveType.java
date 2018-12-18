package app.domain.moving;

import app.domain.ChessBoard;
import app.domain.InvalidPositionException;
import app.domain.Position;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

@Slf4j
public enum MoveType implements MoveDescriber {
    FORWARD_DIAGONAL_LEFT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
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
                                    log.info("integer: " + integer);
                                    log.info(ex.toString());
                                    ;
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
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
                                    log.info(ex.toString());
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
            }
            return validPositions;
        }


    },
    FORWARD_DIAGONAL_RIGHT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
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
                                    log.info(ex.toString());
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
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
                                    log.info(ex.toString());
                                }
                            });
                    validPositions.addAll(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
                    break;
            }
            return validPositions;
        }
    },
    FORWARD {
        @Override
        public TreeSet<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            MoveIterator moveIterator = new MoveIterator();
            List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, (integer, integer2) -> integer, (integer, integer2) -> integer + integer2);
            return new TreeSet<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
        }
    },
    BACKWARD_DIAGONAL_LEFT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            MoveIterator moveIterator = new MoveIterator();
            List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, (integer, integer2) -> integer - integer2, (integer, integer2) -> integer - integer2);
            return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
        }
    },
    BACKWARD_DIAGONAL_RIGHT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            MoveIterator moveIterator = new MoveIterator();
            List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, (integer, integer2) -> integer + integer2, (integer, integer2) -> integer - integer2);
            return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
        }
    },
    BACKWARD {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            MoveIterator moveIterator = new MoveIterator();
            List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, (integer, integer2) -> integer, (integer, integer2) -> integer - integer2);
            return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
        }
    },
    LEFT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            MoveIterator moveIterator = new MoveIterator();
            List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, (integer, integer2) -> integer - integer2, (integer, integer2) -> integer);
            return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
        }
    },
    RIGHT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            MoveIterator moveIterator = new MoveIterator();
            List<Position> possiblePositions = moveIterator.iterate(moveSettings, this, (integer, integer2) -> integer + integer2, (integer, integer2) -> integer);
            return new ArrayList<>(moveSettings.getMovingRule().removeInvalidPositions(chessBoard, this, moveSettings.getCurrentPosition(), moveSettings.getPiece(), possiblePositions));
        }
    },
    KNIGHT {
        @Override
        public List<Position> checkMove(ChessBoard chessBoard, MoveSettings moveSettings) {
            return null;
        }
    };


}