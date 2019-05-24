package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KingThreatTest {
    private BishopTurnValidator bishopTurnValidator = new BishopTurnValidator();
    private KingTurnValidator kingTurnValidator = new KingTurnValidator();
    private KnightTurnValidator knightTurnValidator = new KnightTurnValidator();
    private PawnTurnValidator pawnTurnValidator = new PawnTurnValidator();
    private QuineTurnValidator quineTurnValidator = new QuineTurnValidator();
    private RookTurnValidator rookTurnValidator = new RookTurnValidator();
    private ChessBoard chessBoard;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void knightAndPawnTest() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.Black), "C8");
        chessBoard.addUnit(new Knight(Team.Black), "C7");
        chessBoard.addUnit(new Bishop(Team.Black), "F7");

        chessBoard.addUnit(new King(Team.White), "A2");
        chessBoard.addUnit(new Pawn(Team.White), "B3");
        chessBoard.addUnit(new Rook(Team.White), "C1");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = knightTurnValidator.getAcceptableCoordinatesToMove(chessBoard, "C7");
        assertEquals(0, ac.size());

        ac = pawnTurnValidator.getAcceptableCoordinatesToMove(chessBoard, "B3");
        assertEquals(0, ac.size());
    }

    @Test
    public void bishopTest() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.Black), "C8");
        chessBoard.addUnit(new Bishop(Team.Black), "C7");

        chessBoard.addUnit(new Rook(Team.White), "C1");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = bishopTurnValidator.getAcceptableCoordinatesToMove(chessBoard, "C7");
        assertEquals(0, ac.size());
    }

    @Test
    public void pawnTest() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.Black), "C8");
        chessBoard.addUnit(new Pawn(Team.Black), "C7");

        chessBoard.addUnit(new Rook(Team.White), "C1");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = pawnTurnValidator.getAcceptableCoordinatesToMove(chessBoard, "C7");
        assertEquals(2, ac.size());
    }

    @Test
    public void quineTest() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.Black), "C8");
        chessBoard.addUnit(new Quine(Team.Black), "C7");

        chessBoard.addUnit(new Rook(Team.White), "C1");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = quineTurnValidator.getAcceptableCoordinatesToMove(chessBoard, "C7");
        assertEquals(6, ac.size());
    }

    @Test
    public void rookTest() {
        chessBoard = new ChessBoard();

        chessBoard.addUnit(new King(Team.Black), "C8");
        chessBoard.addUnit(new Rook(Team.Black), "C7");

        chessBoard.addUnit(new Rook(Team.White), "C1");

        BoardVisualizator.show(chessBoard);

        Collection<String> ac = rookTurnValidator.getAcceptableCoordinatesToMove(chessBoard, "C7");
        assertEquals(6, ac.size());
    }
}