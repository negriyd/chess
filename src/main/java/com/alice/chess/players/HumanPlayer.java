package com.alice.chess.players;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer implements Player {
    public HumanPlayer(Team team, ChessBoard chessBoard) {
        super(team, chessBoard);
    }

    @Override
    public String getNextTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.format("Enter %s team next turn: ", this.team);
        return scanner.nextLine();
    }
}
