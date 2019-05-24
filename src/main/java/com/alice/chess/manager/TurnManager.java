package com.alice.chess.manager;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.customException.BadTurnException;
import com.alice.chess.customException.BadTurnFormatException;
import com.alice.chess.customException.UnacceptableTurnException;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.Unit;
import com.alice.chess.unitTurnValidator.UnitTurnValidator;
import com.alice.chess.unitTurnValidator.UnitTurnValidatorFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TurnManager {

    private ChessBoard chessBoard;
    private Unit turnUnit;
    private String from;
    private UnitTurnValidatorFactory unitTurnValidatorFactory;

    public TurnManager(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.unitTurnValidatorFactory = new UnitTurnValidatorFactory();
    }

    /**
     *
     * @param team - White or Black team
     * @param turn - string like "A1 A2" that means move ubit from cell A1 to cell A2
     * @return true is turn is acceplable. Else exception will raise
     * @throws BadTurnException raised if turn is invalid
     */
    public boolean validateTurn(Team team, String turn) throws BadTurnException {
        validateTurnRegexp(turn);
        validateAcceptableTurn(team, turn);

        return true;
    }

    /**
     * Raise exception if turn is unacceptable
     *
     * @param team - means team try to turn (White or Black)
     * @param turn - string like "A1 C5" describe turn from cell A1 to cell C5
     * @throws UnacceptableTurnException
     */
    private void validateAcceptableTurn(Team team, String turn) throws UnacceptableTurnException {
        String[] words = turn.split(" ");
        from = words[0];
        turnUnit = chessBoard.getUnit(from);
        // Check team correctness
        if (turnUnit == null || !turnUnit.getTeam().equals(team))
            throw new UnacceptableTurnException("You can turn " + team + " unit only");

        checkUnitTurnRules(turn);
    }

    /**
     * Raise exception if turn is unacceptable.
     * Call unit specific validator to check proposal turn
     *
     * @param turn - string like "A1 A2" that means move ubit from cell A1 to cell A2
     * @throws UnacceptableTurnException
     */
    private void checkUnitTurnRules(String turn) throws UnacceptableTurnException {
        UnitTurnValidator validator = this.unitTurnValidatorFactory.getUnitTurnValidator(turnUnit.getUnitType());
        validator.validate(chessBoard, turn);
    }

    /**
     * Validate turn string format
     *
     * @param turn - string like "A1 A2" that means move ubit from cell A1 to cell A2
     * @throws BadTurnFormatException
     */
    private void validateTurnRegexp(String turn) throws BadTurnFormatException {
        Pattern pattern = Pattern.compile("^[a-hA-H][1-8] [a-hA-H][1-8]$");
        Matcher matcher = pattern.matcher(turn);
        if (!matcher.matches()) throw new BadTurnFormatException("Bad turn string format");
    }
}
