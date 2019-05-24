package com.alice.chess.manager;

import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.customException.BadTurnException;
import com.alice.chess.players.BotPlayer;
import com.alice.chess.players.HumanPlayer;
import com.alice.chess.players.Player;
import com.alice.chess.termin.Team;
import com.alice.chess.unitTurnValidator.TurnValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class GameManager {
    private ChessBoardFactory chessBoardFactory;
    private TurnManager turnManager;
    private Player whitePlayer, blackPlayer;
    private ChessBoard chessBoard;

    @Autowired
    public GameManager(ChessBoardFactory chessBoardFactory) {
        this.chessBoardFactory = chessBoardFactory;
        this.chessBoard = chessBoardFactory.getStartBoard();
        this.turnManager = new TurnManager(this.chessBoard);
    }

    public void start() {
        setPlayers();
        Player activePlayer = whitePlayer;
        while(
                !TurnValidationUtils.isCheckmate(chessBoard, activePlayer.getTeam()) &&
                !TurnValidationUtils.isStalemate(chessBoard, activePlayer.getTeam())
        ){
            BoardVisualizator.show(this.chessBoard);
            String nextTurn = activePlayer.getNextTurn();
            try {
                turnManager.validateTurn(activePlayer.getTeam(), nextTurn);
                chessBoard.move(nextTurn);
            } catch (BadTurnException e) {
                System.out.println(e.getMessage());
                continue;
            }

            activePlayer = activePlayer == whitePlayer ? blackPlayer : whitePlayer;
        }
    }

    /**
     * Request user to input players info (who is who)
      */
    private void setPlayers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("White team is Human (h) or Bot(b)?: ");
        String whiteTeamType = scanner.nextLine();
        switch (whiteTeamType) {
            case "h": whitePlayer = new HumanPlayer(Team.White, chessBoard); break;
            case "b": whitePlayer = new BotPlayer(Team.White, chessBoard); break;
            default: whitePlayer = new BotPlayer(Team.White, chessBoard);
        }

        System.out.println("Black team is Human (h) or Bot(b)?: ");
        String blackTeamType = scanner.nextLine();
        switch (blackTeamType) {
            case "h": blackPlayer = new HumanPlayer(Team.Black, chessBoard); break;
            case "b": blackPlayer = new BotPlayer(Team.Black, chessBoard); break;
            default: blackPlayer = new BotPlayer(Team.Black, chessBoard);
        }
    }
}
