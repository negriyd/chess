package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.King;
import com.alice.chess.unit.Unit;
import java.awt.Point;
import java.util.Set;
import java.util.stream.Collectors;

public class TurnValidationUtils {
    public static Set<String> getCellsUnderTeamAttack(ChessBoard chessBoard, Team team) {
        UnitTurnValidatorFactory unitTurnValidatorFactory = new UnitTurnValidatorFactory();

        return chessBoard.getTeamCells(team).stream().flatMap(coordinate -> {
            Unit unit = chessBoard.getUnit(coordinate);
            return unitTurnValidatorFactory.getUnitTurnValidator(unit.getUnitType()).getAcceptableCoordinatesToAttack(chessBoard, coordinate).stream();
        }).collect(Collectors.toSet());
    }

    public static boolean isKingUnderAttack(ChessBoard chessBoard, Team team) {
        Team enemyTeam = team.equals(Team.Black) ? Team.White : Team.Black;
        return getCellsUnderTeamAttack(chessBoard, enemyTeam).contains(getKingCoordinate(chessBoard, team));
    }


    private static String getKingCoordinate(ChessBoard chessBoard, Team team) {
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                Unit unit = chessBoard.getUnit(x, y);
                if (unit != null && unit.getTeam() == team && unit.getClass() == King.class)
                    return chessBoard.pointToCoordinate(new Point(x, y));
            }
        }
        return null;
    }

    public static Set<String> getTeamAvailableTurns(ChessBoard chessBoard, Team team) {
        UnitTurnValidatorFactory unitTurnValidatorFactory = new UnitTurnValidatorFactory();
        return chessBoard.getTeamCells(team).stream()
                .flatMap(from -> {
                    return unitTurnValidatorFactory
                            .getUnitTurnValidator(chessBoard.getUnit(from).getUnitType())
                            .getAcceptableCoordinatesToMove(chessBoard, from)
                            .stream()
                            .map(to -> from + " " + to);
                })
                .collect(Collectors.toSet());
    }

    public static boolean isCheckmate(ChessBoard chessBoard, Team team) {
        if (isKingUnderAttack(chessBoard, team) && getTeamAvailableTurns(chessBoard, team).size() == 0) {
            Team enemyTeam = team.equals(Team.Black) ? Team.White : Team.Black;
            BoardVisualizator.show(chessBoard);
            System.out.println("*** Checkmate! " + enemyTeam + " team win!");
            return true;
        } else {
            return false;
        }
    }

    public static boolean isStalemate(ChessBoard chessBoard, Team team) {
        if (!isKingUnderAttack(chessBoard, team) && getTeamAvailableTurns(chessBoard, team).size() == 0 ||
                chessBoard.getAllUnitsOnBoard().size() == 2
        ) {
            BoardVisualizator.show(chessBoard);
            System.out.println("*** Stalemate! No winners.");
            return true;
        } else {
            return false;
        }
    }
}
