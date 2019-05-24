package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.unit.Unit;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class KnightTurnValidator extends AbstractUnitTurnValidator {
    @Override
    public Set<String> getAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate) {
        return getDraftAcceptableCoordinatesToMove(chessBoard, coordinate).stream()
                // except turns where will king threat
                .filter(c -> {
                    ChessBoard futureChessBoard = chessBoard.copy();
                    futureChessBoard.move(coordinate + " " + c);
                    return !TurnValidationUtils.isKingUnderAttack(futureChessBoard, chessBoard.getUnit(coordinate).getTeam());
                })
                .collect(Collectors.toSet());
    }

    /**
     * Make set of available cells to move without care about King safety
     *
     * @param chessBoard
     * @param coordinate - unit coordinate like "A1", "B5", ...
     * @return
     */
    private Set<String> getDraftAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate) {
        Unit unit = chessBoard.getUnit(coordinate);
        Point point = chessBoard.coordinateToPoint(coordinate);

        Set<String> availablePoints = new HashSet<>();
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x - 2, point.y + 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x - 1, point.y + 2)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 1, point.y + 2)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 2, point.y + 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 2, point.y - 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 1, point.y - 2)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x - 1, point.y - 2)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x - 2, point.y - 1)));

        return availablePoints.stream()
                .filter(c -> UnitTurnValidator.isCoordinateRegexpOk(c))
                .filter(c -> chessBoard.getUnit(c) == null || chessBoard.getUnit(c).getTeam() != unit.getTeam())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAcceptableCoordinatesToAttack(ChessBoard chessBoard, String coordinate) {
        return getDraftAcceptableCoordinatesToMove(chessBoard, coordinate);
    }
}
