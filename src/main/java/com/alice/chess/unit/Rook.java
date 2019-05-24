package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class Rook extends Unit {

    public Rook(Team team) {
        super(team);
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.Rook;
    }

    @Override
    public String toString() {
        switch (this.team) {
            case White: return String.valueOf((char)9814);
            case Black: return String.valueOf((char)9820);
        }
        return "?";
    }
}
