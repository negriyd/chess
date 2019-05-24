package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class TurnValidationUtilsTest {
    private ChessBoard chessBoard;

    @Before
    public void setUp() {

    }

    @Test
    public void getCellsUnderTeamAttack() {
        chessBoard = new ChessBoard();
        chessBoard.addUnit(new Pawn(Team.Black), "B7");
        chessBoard.addUnit(new Knight(Team.Black), "F6");
        chessBoard.addUnit(new Quine(Team.White), "A1");
        chessBoard.addUnit(new King(Team.White), "G4");

        Set<String> cellsUnderBlackTeamAttack = TurnValidationUtils.getCellsUnderTeamAttack(chessBoard, Team.Black);
        Set<String> cellsUnderWhiteTeamAttack = TurnValidationUtils.getCellsUnderTeamAttack(chessBoard, Team.White);

        assertEquals(10, cellsUnderBlackTeamAttack.size());
        assertEquals(27, cellsUnderWhiteTeamAttack.size());
    }

    @Test
    public void getTeamAvailableTurnsTest() {
        chessBoard = new ChessBoard();
        chessBoard.addUnit(new Knight(Team.Black), "A2");
        chessBoard.addUnit(new Bishop(Team.Black), "F6");

        Set<String> list = TurnValidationUtils.getTeamAvailableTurns(chessBoard, Team.Black);
        assertEquals(14, list.size());
    }

    @Test
    public void isCheckmateTest() {
        chessBoard = new ChessBoard();
        chessBoard.addUnit(new King(Team.Black), "H8");
        chessBoard.addUnit(new Quine(Team.White), "G7");

        assertFalse("King can kill Quine", TurnValidationUtils.isCheckmate(chessBoard, Team.Black));

        chessBoard.addUnit(new Rook(Team.White), "G1");
        assertTrue("King can't kill Quine, because it covered by Rook", TurnValidationUtils.isCheckmate(chessBoard, Team.Black));
    }

    @Test
    public void isStalemateTest() {
        chessBoard = new ChessBoard();
        chessBoard.addUnit(new King(Team.Black), "A5");
        chessBoard.addUnit(new Quine(Team.White), "C6");
        chessBoard.addUnit(new King(Team.White), "C4");
        assertTrue("King is not under attack and can't move", TurnValidationUtils.isStalemate(chessBoard, Team.Black));
    }


}