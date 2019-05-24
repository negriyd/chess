package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.unit.Unit;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RookTurnValidator extends AbstractUnitTurnValidator {
    @Override
    public Set<String> getAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate) {
        return getDraftAcceptableCoordinatesToMove(chessBoard, coordinate)
                .stream()
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
        return getDraftAcceptableCoordinatesToMove(chessBoard, coordinate);
    }

    private Set<String> getDraftAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate) {
        Set<String> coordinates = new HashSet<>();
        Unit unit = chessBoard.getUnit(coordinate);
        Point point = chessBoard.coordinateToPoint(coordinate);
        // Go up
        coordinates.addAll(UnitTurnValidator.goCheckLine(chessBoard, point.x, point.y+1, 0, 1, unit.getTeam()));
        // Go down
        coordinates.addAll(UnitTurnValidator.goCheckLine(chessBoard, point.x, point.y-1, 0, -1, unit.getTeam()));
        // Go left
        coordinates.addAll(UnitTurnValidator.goCheckLine(chessBoard, point.x-1, point.y, -1, 0, unit.getTeam()));
        // Go right
        coordinates.addAll(UnitTurnValidator.goCheckLine(chessBoard, point.x+1, point.y, 1, 0, unit.getTeam()));
        return coordinates;
    }
}
