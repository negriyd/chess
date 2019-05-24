package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;

public class Pawn extends Unit {

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public UnitType getUnitType() {
        return UnitType.Pawn;
    }

    @Override
    public String toString() {
        switch (this.team) {
            case White: return String.valueOf((char)9817);
            case Black: return String.valueOf((char)9823);
        }
        return "?";
    }
}
