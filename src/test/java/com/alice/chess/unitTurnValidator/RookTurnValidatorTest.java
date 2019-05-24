package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class RookTurnValidatorTest {
    private RookTurnValidator validator;
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {
        validator = new RookTurnValidator();
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new Rook(Team.White), "A1");
        chessBoard.addUnit(new Rook(Team.White), "E3");
        chessBoard.addUnit(new Rook(Team.Black), "C7");
        chessBoard.addUnit(new Rook(Team.Black), "H3");

        BoardVisualizator.show(chessBoard);
    }

    @Test
    public void getAcceptableCoordinatesToMove() {
        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "A1");
        assertEquals(14, ac.size());
        assertTrue(ac.contains("A2"));
        assertTrue(ac.contains("A3"));
        assertTrue(ac.contains("A4"));
        assertTrue(ac.contains("A5"));
        assertTrue(ac.contains("A6"));
        assertTrue(ac.contains("A7"));
        assertTrue(ac.contains("A8"));
        assertTrue(ac.contains("B1"));
        assertTrue(ac.contains("C1"));
        assertTrue(ac.contains("D1"));
        assertTrue(ac.contains("E1"));
        assertTrue(ac.contains("F1"));
        assertTrue(ac.contains("G1"));
        assertTrue(ac.contains("H1"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "E3");
        assertEquals(14, ac.size());
        assertTrue(ac.contains("A3"));
        assertTrue(ac.contains("B3"));
        assertTrue(ac.contains("C3"));
        assertTrue(ac.contains("D3"));
        assertTrue(ac.contains("F3"));
        assertTrue(ac.contains("G3"));
        assertTrue(ac.contains("H3"));
        assertTrue(ac.contains("E1"));
        assertTrue(ac.contains("E2"));
        assertTrue(ac.contains("E4"));
        assertTrue(ac.contains("E5"));
        assertTrue(ac.contains("E6"));
        assertTrue(ac.contains("E7"));
        assertTrue(ac.contains("E8"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "C7");
        assertEquals(14, ac.size());
        assertTrue(ac.contains("C1"));
        assertTrue(ac.contains("C2"));
        assertTrue(ac.contains("C3"));
        assertTrue(ac.contains("C4"));
        assertTrue(ac.contains("C5"));
        assertTrue(ac.contains("C6"));
        assertTrue(ac.contains("C8"));
        assertTrue(ac.contains("A7"));
        assertTrue(ac.contains("B7"));
        assertTrue(ac.contains("D7"));
        assertTrue(ac.contains("E7"));
        assertTrue(ac.contains("F7"));
        assertTrue(ac.contains("G7"));
        assertTrue(ac.contains("H7"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "H3");
        assertEquals(10, ac.size());
        assertTrue(ac.contains("H1"));
        assertTrue(ac.contains("H2"));
        assertTrue(ac.contains("H4"));
        assertTrue(ac.contains("H5"));
        assertTrue(ac.contains("H6"));
        assertTrue(ac.contains("H7"));
        assertTrue(ac.contains("H8"));
        assertTrue(ac.contains("G3"));
        assertTrue(ac.contains("F3"));
        assertTrue(ac.contains("E3"));
    }
}