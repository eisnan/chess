package chess.domain;

import chess.domain.moving.PlayerMove;
import chess.domain.moving.validators.KPositionValidator;
import chess.domain.start.HardCodedPositionResolver;
import chess.domain.start.StartPositionResolver;
import chess.domain.util.Pair;
import com.google.common.eventbus.EventBus;
import com.google.inject.internal.util.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class ChessBoard {

    private final KPositionValidator kPositionValidator = new KPositionValidator();
    private Map<Position, Piece> model = new LinkedHashMap<>();
    private StartPositionResolver startPositionResolver = new HardCodedPositionResolver();
    private LinkedList<PlayerMove> playerMoves = new LinkedList<>();
    private EventBus eventBus = new EventBus();

    public final QChessBoard q;

    public Map<Position, Piece> getModel() {
        return model;
    }

    public Piece get(Position position) {
        return model.get(position);
    }

    public ChessBoard() {
        this.initModel();
        this.arrangePiecesForStart();
        eventBus.register(kPositionValidator);
        q = new QChessBoard();
    }


    private void initModel() {
        List<Rank> ranks = Lists.newArrayList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            for (File file : File.values()) {
                model.put(Position.ofValid(file, rank), null);
            }
        }
    }

    private void arrangePiecesForStart() {
        Map<Piece, Set<Position>> initialPositions = startPositionResolver.getInitialPositions();
        updateModel(initialPositions);
    }

    private void updateModel(Map<Piece, Set<Position>> initialPositions) {
        initialPositions.forEach((piece, positions) -> positions.forEach(position -> model.put(position, piece)));
    }

    public void addMove(PlayerMove playerMove) {
        playerMoves.add(playerMove);
    }

    public void addMoves(Collection<PlayerMove> playerMoves) {
        playerMoves.forEach(move -> {
            playerMoves.add(move);
        });

    }

    public PlayerMove getLastMove() {
        return playerMoves.isEmpty() ? null : playerMoves.getLast();
    }

    public class QChessBoard {

        private final Predicate<Map.Entry<Position, Piece>> POSITIONS_WITH_NO_PIECES = positionPieceEntry -> positionPieceEntry.getValue() != null;

        private QChessBoard() {
        }

        /**
         * a 1-8
         * b 1-8
         *
         * @return
         */
        public Piece[][] getArrayModel() {

            Piece[][] arrayModel = new Piece[8][8];
            for (int j = Rank.values().length - 1; j >= 0; j--) {
                for (int i = 0; i < File.values().length; i++) {
                    arrayModel[i][j] = model.get(Position.ofValid(File.values()[i], Rank.values()[j]));
                }
            }

            return arrayModel;
        }

        public List<Position> get(Piece piece) {
            return model.entrySet().stream()
                    .filter(POSITIONS_WITH_NO_PIECES)
                    .filter(positionPieceEntry -> positionPieceEntry.getValue().equals(piece)).map(Map.Entry::getKey).collect(Collectors.toList());
        }


        public boolean isEmpty(Position position) {
            return getModel().get(position) == null;
        }

        public boolean isNotEmpty(Position position) {
            return !isEmpty(position);
        }

        public Pair<Position, Piece> getKing(PieceColor pieceColor) {

            Optional<Pair<Position, Piece>> king = model.entrySet().stream()
                    .filter(POSITIONS_WITH_NO_PIECES)
                    .filter(positionPieceEntry -> !positionPieceEntry.getValue().canBeCaptured() && positionPieceEntry.getValue().getPieceColor() == pieceColor)
                    .map(positionPieceEntry -> new Pair<>(positionPieceEntry.getKey(), positionPieceEntry.getValue())).findFirst();

            if (!king.isPresent()) {
                throw new InvalidStateException();
            }

            return king.get();
        }

        public Collection<Pair<Position, Piece>> getPieces(PieceType pieceType, PieceColor pieceColor) {
            return getModel().entrySet().stream()
                    .filter(POSITIONS_WITH_NO_PIECES)
                    .filter(positionPieceEntry -> positionPieceEntry.getValue().getPieceType() == pieceType && positionPieceEntry.getValue().getPieceColor() == pieceColor)
                    .map(positionPieceEntry -> new Pair<>(positionPieceEntry.getKey(), positionPieceEntry.getValue())).collect(Collectors.toSet());
        }


        public Collection<Pair<Position, Piece>> getPieces(PieceColor pieceColor, PieceType... pieceType) {
            return getModel().entrySet().stream()
                    .filter(POSITIONS_WITH_NO_PIECES)
                    .filter(positionPieceEntry -> Arrays.asList(pieceType).contains(positionPieceEntry.getValue().getPieceType()))
                    .filter(positionPieceEntry -> positionPieceEntry.getValue().getPieceColor() == pieceColor)
                    .map(positionPieceEntry -> new Pair<>(positionPieceEntry.getKey(), positionPieceEntry.getValue())).collect(Collectors.toSet());
        }

        public Collection<Position> getAdjacentPositions(Position position) {
            Collection<Position> positions = new LinkedHashSet<>();
            int i = position.getFile().ordinal();
            int j = position.getRank().ordinal();

            for (int f = i - 1; f <= i + 1; f++) {
                for (int r = j - 1; r <= j + 1; r++) {
                    if (i == f && j == r) {
                        continue;
                    }
                    Position.of(f, r).ifPresent(positions::add);
                }
            }

            return positions;
        }
    }
}
