package com.alice.chess.manager;

import com.alice.chess.board.ChessBoard;
import com.alice.chess.termin.Team;
import com.alice.chess.termin.UnitType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ChessBoardFactory {

    /**
     * Make initial chess board with all uhnits
     *
     * @return
     */
    public ChessBoard getStartBoard() {
        ChessBoard chessBoard = new ChessBoard();

        // White team
        chessBoard.addUnit(UnitFactory.get(UnitType.Rook, Team.White), "A1");
        chessBoard.addUnit(UnitFactory.get(UnitType.Knight, Team.White), "B1");
        chessBoard.addUnit(UnitFactory.get(UnitType.Bishop, Team.White), "C1");
        chessBoard.addUnit(UnitFactory.get(UnitType.Quine, Team.White), "D1");
        chessBoard.addUnit(UnitFactory.get(UnitType.King, Team.White), "E1");
        chessBoard.addUnit(UnitFactory.get(UnitType.Bishop, Team.White), "F1");
        chessBoard.addUnit(UnitFactory.get(UnitType.Knight, Team.White), "G1");
        chessBoard.addUnit(UnitFactory.get(UnitType.Rook, Team.White), "H1");

        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "A2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "B2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "C2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "D2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "E2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "F2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "G2");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.White), "H2");

        // Black team
        chessBoard.addUnit(UnitFactory.get(UnitType.Rook, Team.Black), "A8");
        chessBoard.addUnit(UnitFactory.get(UnitType.Knight, Team.Black), "B8");
        chessBoard.addUnit(UnitFactory.get(UnitType.Bishop, Team.Black), "C8");
        chessBoard.addUnit(UnitFactory.get(UnitType.Quine, Team.Black), "D8");
        chessBoard.addUnit(UnitFactory.get(UnitType.King, Team.Black), "E8");
        chessBoard.addUnit(UnitFactory.get(UnitType.Bishop, Team.Black), "F8");
        chessBoard.addUnit(UnitFactory.get(UnitType.Knight, Team.Black), "G8");
        chessBoard.addUnit(UnitFactory.get(UnitType.Rook, Team.Black), "H8");

        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "A7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "B7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "C7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "D7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "E7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "F7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "G7");
        chessBoard.addUnit(UnitFactory.get(UnitType.Pawn, Team.Black), "H7");

        return chessBoard;
    }
}
