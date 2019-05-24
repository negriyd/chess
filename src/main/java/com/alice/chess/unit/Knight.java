package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class Knight extends Unit {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.Knight;
    }

    @Override
    public String toString() {
        switch (this.team) {
            case White: return String.valueOf((char)9816);
            case Black: return String.valueOf((char)9822);
        }
        return "?";
    }
}
