package com.alice.chess.players;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unitTurnValidator.TurnValidationUtils;
import java.util.Random;
import java.util.Set;

public class BotPlayer extends AbstractPlayer implements Player {
    public BotPlayer(Team team, ChessBoard chessBoard) {
        super(team, chessBoard);
    }

    @Override
    public String getNextTurn() {
        System.out.format("Enter %s team next turn: ", this.team);
        Set<String> availableTurns = TurnValidationUtils.getTeamAvailableTurns(getChessBoard(), getTeam());

        String turn = getRandomFromSet(availableTurns);

        System.out.println("Bot's turn is " + turn);
        return turn;
    }

    /**
     * Bot just select random turn from available
     *
      * @param availableTurns
     * @return
     */
    private String getRandomFromSet(Set<String> availableTurns) {
        int item = new Random().nextInt(availableTurns.size());
        int i = 0;
        for(String turn : availableTurns) {
            if (i == item)
                return turn;
            i++;
        }
        return null;
    }
}
