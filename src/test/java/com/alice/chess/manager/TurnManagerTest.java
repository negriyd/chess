package com.alice.chess.manager;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.customException.BadTurnException;
import com.alice.chess.customException.BadTurnFormatException;
import com.alice.chess.customException.UnacceptableTurnException;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Bishop;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TurnManagerTest {
    private TurnManager turnManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        ChessBoard chessBoard = chessBoardFactory.getStartBoard();
        chessBoard.addUnit(new Bishop(Team.White), "B3");
        turnManager = new TurnManager(chessBoard);
    }

    @Test
    public void validateTurnBadFormat() throws BadTurnException {
        thrown.expect(BadTurnFormatException.class);
        turnManager.validateTurn(Team.White, "A2? A3");
    }

    @Test
    public void validateTeamСorrectness () throws BadTurnException {
        thrown.expect(UnacceptableTurnException.class);
        thrown.expectMessage("You can turn " + Team.Black + " unit only");
        assertTrue(turnManager.validateTurn(Team.Black,"A2 A3"));
    }

    @Test
    public void validatePawnTurn() throws BadTurnException {
        assertTrue(turnManager.validateTurn(Team.White,"A2 A3")); //Short turn
        assertTrue(turnManager.validateTurn(Team.White,"A2 A4")); // Long turn

        thrown.expect(UnacceptableTurnException.class);
        assertTrue(turnManager.validateTurn(Team.White,"A2 A5")); // Too long turn
    }

    @Test
    public void validateBishopTurn() throws BadTurnException {
        assertTrue(turnManager.validateTurn(Team.White,"B3 D5")); // Go to empty cell
        assertTrue(turnManager.validateTurn(Team.White,"B3 F7")); // Attack enemy

        thrown.expect(UnacceptableTurnException.class);
        assertTrue(turnManager.validateTurn(Team.White,"B3 G8")); //Attack unavailable enemy

        thrown.expect(UnacceptableTurnException.class);
        assertTrue(turnManager.validateTurn(Team.White,"B3 A2")); //Attack friendly unit

        thrown.expect(UnacceptableTurnException.class);
        assertTrue(turnManager.validateTurn(Team.White,"C1 A3")); //Invalid turn
    }
}