package com.alice.chess.manager;

import com.alice.chess.unit.*;
import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class UnitFactory {

    public static Unit get(UnitType unitType, Team team) {
        switch (unitType) {
            case King:
                return new King(team);
            case Quine:
                return new Quine(team);
            case Rook:
                return new Rook(team);
            case Bishop:
                return new Bishop(team);
            case Knight:
                return new Knight(team);
            case Pawn:
                return new Pawn(team);
        }
        return null;
    }
}
