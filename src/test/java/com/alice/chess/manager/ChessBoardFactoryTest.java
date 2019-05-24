package com.alice.chess.manager;

import com.alice.chess.ChessApplication;
import com.alice.chess.board.BoardVisualizator;
import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.unit.King;
import com.alice.chess.unit.Pawn;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChessBoardFactory.class)
public class ChessBoardFactoryTest {
    @Autowired
    private ChessBoardFactory chessBoardFactory;
    private BoardVisualizator boardVisualizator;

    @Test
    public void getStartBoard() {
        ChessBoard board = chessBoardFactory.getStartBoard();
        assertEquals(Team.White, board.getUnit("A2").getTeam());
        assertEquals(Pawn.class, board.getUnit("A2").getClass());

        assertEquals(Team.Black, board.getUnit("E8").getTeam());
        assertEquals(King.class, board.getUnit("E8").getClass());

        BoardVisualizator.show(board);
    }
}