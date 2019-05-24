package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class King extends Unit {

    public King(Team team) {
        super(team);
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.King;
    }

    @Override
    public String toString() {
        switch (this.team) {
            case White: return String.valueOf((char)9812);
            case Black: return String.valueOf((char)9818);
        }
        return "?";
    }
}
