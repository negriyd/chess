package com.alice.chess.board;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;
import com.alice.chess.unit.Quine;
import com.alice.chess.unit.Unit;
import org.springframework.stereotype.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ChessBoard {
    Unit[][] board = new Unit[8][8];

    /**
     * Put a new unit on the board
     * @param unit - chess unit
     * @param coordinate - unit coordinate like "A1", "B5", ...
     */
    public void addUnit(Unit unit, String coordinate) {
        Point point = coordinateToPoint(coordinate);
        board[point.y][point.x] = unit;
    }

    /**
     * Find unit on board by coordinates.
     * 0 <= x <= 7
     * 0 <= y <= 7
     *
     * @param x - chess board coordinate from 0 to 7. A=0, B-1, ...
     * @param y - chess board coordinate from 0 to 7
     * @return null if no unit
     */
    public Unit getUnit(int x, int y) {
        return board[y][x];
    }

    /**
     * Find unit on board by point.
     * 0 <= point.x <= 7
     * 0 <= point.y <= 7
     *
     * @param point - points to chess board cell
     * @return null if no unit
     */
    public Unit getUnit(Point point) {
        return board[point.y][point.x];
    }

    public Unit getUnit(String coordinate) {
        Point point = coordinateToPoint(coordinate);
        return getUnit(point.x, point.y);
    }

    /**
     * Just transform string chess coordinate (A1, C8, ...) to Point
     *
     * @param coordinate - unit coordinate like "A1", "B5", ...
     * @return point to chess board cell
     */
    public Point coordinateToPoint(String coordinate) {
        int x = 0, y = Integer.parseInt(coordinate.substring(1)) - 1;
        switch (coordinate.toUpperCase().charAt(0)) {
            case 'A': x = 0; break;
            case 'B': x = 1; break;
            case 'C': x = 2; break;
            case 'D': x = 3; break;
            case 'E': x = 4; break;
            case 'F': x = 5; break;
            case 'G': x = 6; break;
            case 'H': x = 7; break;
        }
        return new Point(x, y);
    }

    /**
     * Just transform point on chess board to coordinate like A1, B8, ...
     *
     * @param point - points to chess board cell
     * @return chess board cell coordinate (like A1 or G7)
     */
    public String pointToCoordinate(Point point) {
        String letter = null;
        switch (point.x) {
            case 0: letter = "A"; break;
            case 1: letter = "B"; break;
            case 2: letter = "C"; break;
            case 3: letter = "D"; break;
            case 4: letter = "E"; break;
            case 5: letter = "F"; break;
            case 6: letter = "G"; break;
            case 7: letter = "H"; break;
            default: letter = "?";
        }

        return letter + (point.y+1);
    }

    /**
     * Return list of team unit coordinates (strings like A1, B8, ...)
     *
     * @param team
     * @return list of team unit coordinates
     */
    public List<String> getTeamCells(Team team) {
        List<String> result = new ArrayList<>();
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (getUnit(x, y) != null && getUnit(x, y).getTeam().equals(team))
                    result.add(pointToCoordinate(new Point(x, y)));
            }
        }
        return result;
    }

    /**
     * Make a copy of current board
     *
     * @return new ChessBoard like previous
     */
    public ChessBoard copy() {
        ChessBoard newChessBoard = new ChessBoard();
        Point point;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                point = new Point(x, y);
                newChessBoard.addUnit(this.getUnit(point), pointToCoordinate(point));
            }
        }
        return newChessBoard;
    }


    /**
     * Move unit to other chess board cell without validations
     *
     * @param turn - string like "B1 B2" means move unit from B1 to B2
     */
    public void move(String turn) {
        String[] words = turn.split(" ");
        String from = words[0];
        String to = words[1];

        Unit unit = this.getUnit(from);
        this.addUnit(null, from);
        this.addUnit(unit, to);

        // Turn Pawn to Quine if Pawn accessed opposite border
        Unit movedUnit = this.getUnit(to);
        if (
                movedUnit.getUnitType().equals(UnitType.Pawn) &&
                (
                        (
                            movedUnit.getTeam().equals(Team.White) &&
                            coordinateToPoint(to).y == 7
                        ) ||
                        (
                            movedUnit.getTeam().equals(Team.Black) &&
                            coordinateToPoint(to).y == 0
                        )
                )
        )
            this.addUnit(new Quine(movedUnit.getTeam()), to);
    }

    /**
     * @return list of all unit on board
     */
    public List<Unit> getAllUnitsOnBoard() {
        List<Unit> list = new LinkedList<>();
        for (int x = 0; x <= 7; x++)
            for (int y = 0; y <=7; y++)
                if (getUnit(x, y) != null)
                    list.add(getUnit(x, y));
        return list;
    }
}
