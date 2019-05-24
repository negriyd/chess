package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Pawn;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class PawnTurnValidatorTest {

    private PawnTurnValidator validator;
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {
        validator = new PawnTurnValidator();
        chessBoard = new ChessBoard();
        chessBoard.addUnit(new Pawn(Team.White), "A1");
        chessBoard.addUnit(new Pawn(Team.White), "B2");
        chessBoard.addUnit(new Pawn(Team.White), "H2");
        chessBoard.addUnit(new Pawn(Team.White), "C4");
        chessBoard.addUnit(new Pawn(Team.Black), "G3");
        chessBoard.addUnit(new Pawn(Team.White), "D5");
        chessBoard.addUnit(new Pawn(Team.Black), "C6");
        chessBoard.addUnit(new Pawn(Team.Black), "E6");
        chessBoard.addUnit(new Pawn(Team.White), "G5");
        chessBoard.addUnit(new Pawn(Team.Black), "G6");
        chessBoard.addUnit(new Pawn(Team.Black), "A7");

        BoardVisualizator.show(chessBoard);
    }

    @Test
    public void getWhiteAcceptableCoordinatesToMove() {
        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "A1");
        assertEquals(1, ac.size());
        assertTrue(ac.contains("A2"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "B2");
        assertEquals(2, ac.size());
        assertTrue(ac.contains("B3"));
        assertTrue(ac.contains("B4"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "H2");
        assertEquals(3, ac.size());
        assertTrue(ac.contains("H3"));
        assertTrue(ac.contains("H4"));
        assertTrue(ac.contains("G3"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "C4");
        assertEquals(1, ac.size());
        assertTrue(ac.contains("C5"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "D5");
        assertEquals(3, ac.size());
        assertTrue(ac.contains("C6"));
        assertTrue(ac.contains("D6"));
        assertTrue(ac.contains("E6"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "G5");
        assertEquals(0, ac.size());

    }

    @Test
    public void getBlackAcceptableCoordinatesToMove() {
        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "A7");
        assertEquals(2, ac.size());
        assertTrue(ac.contains("A6"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "C6");
        assertEquals(2, ac.size());
        assertTrue(ac.contains("C5"));
        assertTrue(ac.contains("D5"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "E6");
        assertEquals(2, ac.size());
        assertTrue(ac.contains("E5"));
        assertTrue(ac.contains("D5"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "G6");
        assertEquals(0, ac.size());
    }
}