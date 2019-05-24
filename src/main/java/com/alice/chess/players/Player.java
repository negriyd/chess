package com.alice.chess.players;

import com.alice.chess.termin.Team;

public interface Player {
    /**
     * Receive from bot or human next turn
     * @return - string like "E2 E4" that means move unit from cell E2 to cell E4
     */
    String getNextTurn();
    Team getTeam();
}
