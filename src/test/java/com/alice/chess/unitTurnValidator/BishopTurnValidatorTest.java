package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Bishop;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class BishopTurnValidatorTest {
    private BishopTurnValidator validator;
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {
        validator = new BishopTurnValidator();
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new Bishop(Team.White), "A1");
        chessBoard.addUnit(new Bishop(Team.White), "B3");
        chessBoard.addUnit(new Bishop(Team.White), "B7");
        chessBoard.addUnit(new Bishop(Team.Black), "D5");
        chessBoard.addUnit(new Bishop(Team.Black), "E8");
        chessBoard.addUnit(new Bishop(Team.Black), "G6");

        BoardVisualizator.show(chessBoard);
    }

    @Test
    public void getAcceptableCoordinatesToMove() {
        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "A1");
        assertEquals(7, ac.size());
        assertTrue(ac.contains("B2"));
        assertTrue(ac.contains("C3"));
        assertTrue(ac.contains("D4"));
        assertTrue(ac.contains("E5"));
        assertTrue(ac.contains("F6"));
        assertTrue(ac.contains("G7"));
        assertTrue(ac.contains("H8"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "B3");
        assertEquals(6, ac.size());
        assertTrue(ac.contains("A2"));
        assertTrue(ac.contains("C4"));
        assertTrue(ac.contains("D5"));
        assertTrue(ac.contains("A4"));
        assertTrue(ac.contains("C2"));
        assertTrue(ac.contains("D1"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "B7");
        assertEquals(5, ac.size());
        assertTrue(ac.contains("A8"));
        assertTrue(ac.contains("C6"));
        assertTrue(ac.contains("D5"));
        assertTrue(ac.contains("A6"));
        assertTrue(ac.contains("C8"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "E8");
        assertEquals(5, ac.size());
        assertTrue(ac.contains("D7"));
        assertTrue(ac.contains("C6"));
        assertTrue(ac.contains("B5"));
        assertTrue(ac.contains("A4"));
        assertTrue(ac.contains("F7"));
    }
}