package com.alice.chess.unitTurnValidator;

import com.alice.chess.termin.UnitType;
import java.util.HashMap;
import java.util.Map;

public class UnitTurnValidatorFactory {

    private Map<UnitType, UnitTurnValidator> map;

    public UnitTurnValidatorFactory() {
        this.map = new HashMap<>(6);
        this.map.put(UnitType.King, new KingTurnValidator());
        this.map.put(UnitType.Quine, new QuineTurnValidator());
        this.map.put(UnitType.Rook, new RookTurnValidator());
        this.map.put(UnitType.Bishop, new BishopTurnValidator());
        this.map.put(UnitType.Knight, new KnightTurnValidator());
        this.map.put(UnitType.Pawn, new PawnTurnValidator());
    }

    public UnitTurnValidator getUnitTurnValidator(UnitType unitType) {
        return this.map.get(unitType);
    }
}
