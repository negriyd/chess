package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class Quine extends Unit {
    public Quine(Team team) {
        super(team);
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.Quine;
    }

    @Override
    public String toString() {
        switch (this.team) {
            case White: return String.valueOf((char)9813);
            case Black: return String.valueOf((char)9819);
        }
        return "?";
    }
}
