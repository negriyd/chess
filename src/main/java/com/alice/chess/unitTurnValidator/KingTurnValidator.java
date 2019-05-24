package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Unit;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class KingTurnValidator extends AbstractUnitTurnValidator {
    @Override
    public Set<String> getAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate) {
        Team enemyTeam = chessBoard.getUnit(coordinate).getTeam().equals(Team.Black) ? Team.White : Team.Black;
        return getAcceptableCoordinatesToAttack(chessBoard, coordinate).stream()
                .filter(c -> !TurnValidationUtils.getCellsUnderTeamAttack(chessBoard, enemyTeam).contains(c))
                .filter(c -> {
                    ChessBoard futureChessBoard = chessBoard.copy();
                    futureChessBoard.move(coordinate + " " + c);
                    return !TurnValidationUtils.getCellsUnderTeamAttack(futureChessBoard, enemyTeam).contains(c);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAcceptableCoordinatesToAttack(ChessBoard chessBoard, String coordinate) {
        Unit unit = chessBoard.getUnit(coordinate);
        Point point = chessBoard.coordinateToPoint(coordinate);

        Set<String> availablePoints = new HashSet<>();
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x - 1, point.y + 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x, point.y + 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 1, point.y + 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x - 1, point.y)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 1, point.y)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x -1, point.y - 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x, point.y - 1)));
        availablePoints.add(chessBoard.pointToCoordinate(new Point(point.x + 1, point.y - 1)));

        return availablePoints.stream()
                .filter(c -> UnitTurnValidator.isCoordinateRegexpOk(c))
                .filter(c -> chessBoard.getUnit(c) == null || chessBoard.getUnit(c).getTeam() != unit.getTeam())
                .collect(Collectors.toSet());
    }
}