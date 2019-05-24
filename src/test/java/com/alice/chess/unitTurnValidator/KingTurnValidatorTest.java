package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Bishop;
import com.alice.chess.unit.King;
import com.alice.chess.unit.Knight;
import com.alice.chess.unit.Pawn;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class KingTurnValidatorTest {
    private KingTurnValidator validator;
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {
        validator = new KingTurnValidator();
    }

    @Test
    public void getAcceptableCoordinatesToMove() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.White), "B8");
        chessBoard.addUnit(new King(Team.White), "C4");
        chessBoard.addUnit(new King(Team.White), "H1");
        chessBoard.addUnit(new Knight(Team.Black), "D5");
        chessBoard.addUnit(new Knight(Team.White), "D4");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "B8");
        assertEquals(4, ac.size());
        assertTrue(ac.contains("A8"));
        assertTrue(ac.contains("A7"));
        assertTrue(ac.contains("B7"));
        assertTrue(ac.contains("C8"));

        ac = validator.getAcceptableCoordinatesToMove(chessBoard, "C4");
        assertEquals(5, ac.size());
        assertTrue(ac.contains("B5"));
        assertTrue(ac.contains("C5"));
        assertTrue(ac.contains("D5"));
        assertTrue(ac.contains("B3"));
        assertTrue(ac.contains("D3"));
    }

    @Test
    public void getAcceptableCoordinatesToMoveWithThreatToKing() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.White), "A4");
        chessBoard.addUnit(new Knight(Team.Black), "D3");
        chessBoard.addUnit(new Bishop(Team.Black), "D8");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "A4");
        assertEquals(3, ac.size());
        assertTrue(ac.contains("A3"));
        assertTrue(ac.contains("B3"));
        assertTrue(ac.contains("B5"));
    }

    @Test
    public void getAcceptableCoordinatesToMoveWithThreatToKing2() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.White), "A4");
        chessBoard.addUnit(new Pawn(Team.White), "B6");
        chessBoard.addUnit(new Knight(Team.Black), "D3");
        chessBoard.addUnit(new Bishop(Team.Black), "D8");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = validator.getAcceptableCoordinatesToMove(chessBoard, "A4");
        assertEquals(4, ac.size());
        assertTrue(ac.contains("A3"));
        assertTrue(ac.contains("B3"));
        assertTrue(ac.contains("B5"));
        assertTrue(ac.contains("A5"));
    }
}