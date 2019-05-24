package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.customException.UnacceptableTurnException;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Unit;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface UnitTurnValidator {
    void validate(ChessBoard chessBoard, String turn) throws UnacceptableTurnException;

    Set<String> getAcceptableCoordinatesToMove(ChessBoard chessBoard, String coordinate);
    Set<String> getAcceptableCoordinatesToAttack(ChessBoard chessBoard, String coordinate);

    static Set<String> goCheckLine(ChessBoard chessBoard, int x, int y, int incrementX, int incrementY, Team myTeam) {
        Set<String> coordinates = new HashSet<>();
        while (between(x, 0, 7) && between(y, 0, 7)) {
            Unit checkingCell = chessBoard.getUnit(x, y);
            if (checkingCell == null || !checkingCell.getTeam().equals(myTeam)) {
                coordinates.add(chessBoard.pointToCoordinate(new Point(x, y)));
                if(checkingCell != null && !checkingCell.getTeam().equals(myTeam)) break;
            }
            else if (checkingCell != null) {
                if (!checkingCell.getTeam().equals(myTeam))
                    coordinates.add(chessBoard.pointToCoordinate(new Point(x, y)));
                break;
            }
            x = x + incrementX; y = y + incrementY;
        }
        return coordinates;
    }

    static boolean between(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }

    static boolean isCoordinateRegexpOk(String turn) {
        Pattern pattern = Pattern.compile("^[a-hA-H][1-8]$");
        Matcher matcher = pattern.matcher(turn);
        return matcher.matches();
    }
}
