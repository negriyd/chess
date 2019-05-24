package com.alice.chess.unit;

import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Unit {
    protected Team team;

    public Unit(Team team) {
        this.team = team;
    }

    abstract public UnitType getUnitType();
}
