package com.alice.chess.board;

import com.alice.chess.manager.ChessBoardFactory;
import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;
import com.alice.chess.unit.King;
import com.alice.chess.unit.Knight;
import com.alice.chess.unit.Pawn;
import com.alice.chess.unit.Quine;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class ChessBoardTest {
    private ChessBoardFactory chessBoardFactory;
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {
        chessBoardFactory = new ChessBoardFactory();
        chessBoard = chessBoardFactory.getStartBoard();
    }

    @Test
    public void coordinateToPoint() {
        assertEquals(new Point(1, 1), chessBoard.coordinateToPoint("B2"));
        assertEquals(new Point(0, 7), chessBoard.coordinateToPoint("A8"));
        assertEquals(new Point(7, 0), chessBoard.coordinateToPoint("H1"));
    }

    @Test
    public void pointToCoordinate() {
        assertEquals("B2", chessBoard.pointToCoordinate(new Point(1, 1)));
        assertEquals("A8", chessBoard.pointToCoordinate(new Point(0, 7)));
        assertEquals("H1", chessBoard.pointToCoordinate(new Point(7, 0)));
        assertEquals("?1", chessBoard.pointToCoordinate(new Point(8, 0)));
        assertEquals("?1", chessBoard.pointToCoordinate(new Point(-1, 0)));
    }

    @Test
    public void getTeamCells() {
        ChessBoard myChessBoard = new ChessBoard();
        myChessBoard.addUnit(new Pawn(Team.Black), "B7");
        myChessBoard.addUnit(new Knight(Team.Black), "F6");
        myChessBoard.addUnit(new Quine(Team.White), "A1");
        myChessBoard.addUnit(new King(Team.White), "G4");

        assertEquals(2, myChessBoard.getTeamCells(Team.Black).size());
        assertEquals(2, myChessBoard.getTeamCells(Team.White).size());
    }

    @Test
    public void moveTest() {
        ChessBoard myChessBoard = new ChessBoard();
        myChessBoard.addUnit(new Pawn(Team.White), "A7");
        myChessBoard.addUnit(new Pawn(Team.White), "A4");
        myChessBoard.addUnit(new Pawn(Team.Black), "A2");
        myChessBoard.addUnit(new Pawn(Team.Black), "B3");

        myChessBoard.move("A7 A8");
        assertNull(myChessBoard.getUnit("A7"));
        assertNotNull(myChessBoard.getUnit("A8"));
        assertEquals(Team.White, myChessBoard.getUnit("A8").getTeam());
        assertEquals(UnitType.Quine,  myChessBoard.getUnit("A8").getUnitType());

        myChessBoard.move("A4 A5");
        assertNull(myChessBoard.getUnit("A4"));
        assertNotNull(myChessBoard.getUnit("A5"));
        assertEquals(Team.White, myChessBoard.getUnit("A5").getTeam());
        assertEquals(UnitType.Pawn,  myChessBoard.getUnit("A5").getUnitType());

        myChessBoard.move("A2 A1");
        assertNull(myChessBoard.getUnit("A2"));
        assertNotNull(myChessBoard.getUnit("A1"));
        assertEquals(Team.Black, myChessBoard.getUnit("A1").getTeam());
        assertEquals(UnitType.Quine,  myChessBoard.getUnit("A1").getUnitType());

        myChessBoard.move("B3 B2");
        assertNull(myChessBoard.getUnit("B3"));
        assertNotNull(myChessBoard.getUnit("B2"));
        assertEquals(Team.Black, myChessBoard.getUnit("B2").getTeam());
        assertEquals(UnitType.Pawn,  myChessBoard.getUnit("B2").getUnitType());

    }
}