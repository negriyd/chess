package com.alice.chess.board;

import org.springframework.stereotype.Component;

@Component
public class BoardVisualizator {

    /**
     * Draw chess board current state
     * @param chessBoard
     */
    public static void show(ChessBoard chessBoard) {
        String empty = String.valueOf((char) 160);

        System.out.println("    A   B  C   D  E   F  G   H");
        System.out.println("----------------------------------------");

        for (int i = 7; i >= 0; i--) {
            System.out.format("%d|%3s|%3s|%3s|%3s|%3s|%3s|%3s|%3s", i+1,
                    chessBoard.getUnit(0, i) != null ? chessBoard.getUnit(0, i) : empty,
                    chessBoard.getUnit(1, i) != null ? chessBoard.getUnit(1, i) : empty,
                    chessBoard.getUnit(2, i) != null ? chessBoard.getUnit(2, i) : empty,
                    chessBoard.getUnit(3, i) != null ? chessBoard.getUnit(3, i) : empty,
                    chessBoard.getUnit(4, i) != null ? chessBoard.getUnit(4, i) : empty,
                    chessBoard.getUnit(5, i) != null ? chessBoard.getUnit(5, i) : empty,
                    chessBoard.getUnit(6, i) != null ? chessBoard.getUnit(6, i) : empty,
                    chessBoard.getUnit(7, i) != null ? chessBoard.getUnit(7, i) : empty
            );
            System.out.println();
        }
        System.out.println("----------------------------------------");
        System.out.println("    A   B  C   D  E   F  G   H");
    }
}
