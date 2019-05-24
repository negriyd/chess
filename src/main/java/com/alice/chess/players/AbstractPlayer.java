package com.alice.chess.players;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;

public class AbstractPlayer {
    protected Team team;
    protected ChessBoard chessBoard;

    AbstractPlayer(Team team, ChessBoard chessBoard) {
        this.team = team;
        this.chessBoard = chessBoard;
    }

    public Team getTeam() {
        return team;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
