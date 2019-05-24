package com.alice.chess.unitTurnValidator;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.customException.UnacceptableTurnException;
import com.alice.chess.unit.Unit;

public abstract class AbstractUnitTurnValidator implements UnitTurnValidator {
    @Override
    public void validate(ChessBoard chessBoard, String turn) throws UnacceptableTurnException {
        Unit unit = chessBoard.getUnit(turn.split(" ")[0]);
        if (!getAcceptableCoordinatesToMove(chessBoard, turn.split(" ")[0]).contains(turn.split(" ")[1]))
            throw new UnacceptableTurnException("Unacceptable turn for " + unit.getTeam());
    }
}
