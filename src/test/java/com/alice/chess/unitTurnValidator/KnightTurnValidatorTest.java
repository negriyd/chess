package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Knight;
import com.alice.chess.unit.Quine;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class KnightTurnValidatorTest {
    private KnightTurnValidator validator;
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {
        validator = new KnightTurnValidator();
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new Knight(Team.White), "B1");
        chessBoard.addUnit(new Knight(Team.White), "E5");
        chessBoard.addUnit(new Knight(Team.White), "F3");
        chessBoard.addUnit(new Knight(Team.Black), "C6");
        chessBoard.addUnit(new Knight(Team.Black), "H8");

        BoardVisualizator.show(chessBoard);
    }

    @Test
    public void getAcceptableCoordinatesToMove() {
        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "B1");
        assertEquals(3, ac.size());
        assertTrue(ac.contains("A3"));
        assertTrue(ac.contains("C3"));
        assertTrue(ac.contains("D2"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "E5");
        assertEquals(7, ac.size());
        assertTrue(ac.contains("C6"));
        assertTrue(ac.contains("D7"));
        assertTrue(ac.contains("F7"));
        assertTrue(ac.contains("G6"));
        assertTrue(ac.contains("G4"));
        assertTrue(ac.contains("D3"));
        assertTrue(ac.contains("C4"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "F3");
        assertEquals(7, ac.size());
        assertTrue(ac.contains("G5"));
        assertTrue(ac.contains("H4"));
        assertTrue(ac.contains("H2"));
        assertTrue(ac.contains("G1"));
        assertTrue(ac.contains("E1"));
        assertTrue(ac.contains("D2"));
        assertTrue(ac.contains("D4"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "C6");
        assertEquals(8, ac.size());
        assertTrue(ac.contains("A7"));
        assertTrue(ac.contains("B8"));
        assertTrue(ac.contains("D8"));
        assertTrue(ac.contains("E7"));
        assertTrue(ac.contains("E5"));
        assertTrue(ac.contains("D4"));
        assertTrue(ac.contains("B4"));
        assertTrue(ac.contains("A5"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "H8");
        assertEquals(2, ac.size());
        assertTrue(ac.contains("F7"));
        assertTrue(ac.contains("G6"));
    }
}