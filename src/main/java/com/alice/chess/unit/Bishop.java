package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class Bishop extends Unit {

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.Bishop;
    }

    @Override
    public String toString() {
        switch (this.team) {
            case White: return String.valueOf((char)9815);
            case Black: return String.valueOf((char)9821);
        }
        return "?";
    }
}
