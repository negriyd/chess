package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Unit;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnTurnValidator extends AbstractUnitTurnValidator {
    @Override
    public Set<String> getAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate) {
        Set<String> coordinates = new HashSet<>();
        Unit unit = chessBoard.getUnit(coordinate);
        Point point = chessBoard.coordinateToPoint(coordinate);

        switch (unit.getTeam()) {
            case White:
                // Standard Pawn move forward
                String newCoordinate = chessBoard.pointToCoordinate(new Point(point.x, point.y + 1));
                if (UnitTurnValidator.isCoordinateRegexpOk(newCoordinate) && chessBoard.getUnit(newCoordinate) == null)
                    coordinates.add(chessBoard.pointToCoordinate(new Point(point.x, point.y + 1)));
                // Long Pawn move
                if (point.y == 1 && chessBoard.getUnit(point.x, point.y+1) == null && chessBoard.getUnit(point.x, point.y+2) == null)
                    coordinates.add(chessBoard.pointToCoordinate(new Point(point.x, point.y + 2)));
                // Fight with enemy available
                Point fightPointLeft = new Point(point.x - 1, point.y + 1);
                if (
                        UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointLeft)) &&
                        chessBoard.getUnit(fightPointLeft) != null &&
                        chessBoard.getUnit(fightPointLeft).getTeam().equals(Team.Black)
                    )
                    coordinates.add(chessBoard.pointToCoordinate(fightPointLeft));
                Point fightPointRight = new Point(point.x + 1, point.y + 1);
                if (
                        UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointRight)) &&
                                chessBoard.getUnit(fightPointRight) != null &&
                                chessBoard.getUnit(fightPointRight).getTeam().equals(Team.Black)
                )
                    coordinates.add(chessBoard.pointToCoordinate(fightPointRight));

                break;
            case Black:
                // Standard Pawn move forward
                newCoordinate = chessBoard.pointToCoordinate(new Point(point.x, point.y - 1));
                if (UnitTurnValidator.isCoordinateRegexpOk(newCoordinate) && chessBoard.getUnit(newCoordinate) == null)
                    coordinates.add(chessBoard.pointToCoordinate(new Point(point.x, point.y - 1)));
                // Long Pawn move
                if (point.y == 6 && chessBoard.getUnit(point.x, point.y-1) == null && chessBoard.getUnit(point.x, point.y-2) == null)
                    coordinates.add(chessBoard.pointToCoordinate(new Point(point.x, point.y - 2)));
                // Fight with enemy available
                fightPointLeft = new Point(point.x - 1, point.y - 1);
                if (
                        UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointLeft)) &&
                                chessBoard.getUnit(fightPointLeft) != null &&
                                chessBoard.getUnit(fightPointLeft).getTeam().equals(Team.White)
                )
                    coordinates.add(chessBoard.pointToCoordinate(fightPointLeft));
                fightPointRight = new Point(point.x + 1, point.y - 1);
                if (
                        UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointRight)) &&
                                chessBoard.getUnit(fightPointRight) != null &&
                                chessBoard.getUnit(fightPointRight).getTeam().equals(Team.White)
                )
                    coordinates.add(chessBoard.pointToCoordinate(fightPointRight));

                break;
        }
        return coordinates.stream()
                // except turns where will king threat
                .filter(c -> {
                    ChessBoard futureChessBoard = chessBoard.copy();
                    futureChessBoard.move(coordinate + " " + c);
                    return !TurnValidationUtils.isKingUnderAttack(futureChessBoard, chessBoard.getUnit(coordinate).getTeam());
                })
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAcceptableCoordinatesToAttack(ChessBoard chessBoard, String coordinate) {
        Set<String> coordinates = new HashSet<>();
        Unit unit = chessBoard.getUnit(coordinate);
        Point point = chessBoard.coordinateToPoint(coordinate);

        switch (unit.getTeam()) {
            case White:
                // Fight with enemy available
                Point fightPointLeft = new Point(point.x - 1, point.y + 1);
                if (UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointLeft)))
                    coordinates.add(chessBoard.pointToCoordinate(fightPointLeft));
                Point fightPointRight = new Point(point.x + 1, point.y + 1);
                if (UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointRight)))
                    coordinates.add(chessBoard.pointToCoordinate(fightPointRight));
                break;
            case Black:
                // Fight with enemy available
                fightPointLeft = new Point(point.x - 1, point.y - 1);
                if (UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointLeft)))
                    coordinates.add(chessBoard.pointToCoordinate(fightPointLeft));
                fightPointRight = new Point(point.x + 1, point.y - 1);
                if (UnitTurnValidator.isCoordinateRegexpOk(chessBoard.pointToCoordinate(fightPointRight)))
                    coordinates.add(chessBoard.pointToCoordinate(fightPointRight));
                break;
        }
        return coordinates;
    }
}
