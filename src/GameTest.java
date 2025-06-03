import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameTest {

    @Test
    void testKingIsInCheck() {
        Game game = new Game();
        ChessBoard board = game.getChessBoard();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        board.setPiece(7, 4, new King(true));
        board.setPiece(0, 4, new Rook(false));

        assertTrue(game.isKingInCheck(true, board), "White king should be in check from rook.");
    }

    @Test
    void testNoCastlingWhileInCheck() {
        Game game = new Game();
        ChessBoard board = game.getChessBoard();

        King whiteKing = new King(true);
        board.setPiece(7, 4, whiteKing);
        board.setPiece(7, 7, new Rook(true));

        // Black bishop is attacking the king
        board.setPiece(5, 6, new Bishop(false));

        ArrayList<Move> moves = whiteKing.getLegalMoves(7, 4, board);
        boolean hasCastle = false;
        for (Move m : moves) {
            if (m.isCastleKingSide()) {
                hasCastle = true;
                break;
            }
        }


        assertFalse(hasCastle, "Castling should not be allowed while in check or through attacked square.");
    }

    @Test
    void testGameOverCheckmate() {
        Game game = new Game();
        ChessBoard board = game.getChessBoard();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board.setPiece(r, c, null);
            }
        }

        // Setup for a mate
        board.setPiece(0, 0, new King(false));
        board.setPiece(1, 1, new Queen(true));
        board.setPiece(2, 2, new Bishop(true));

        game.switchTurn();
        game.checkEndConditions();

        assertTrue(game.isGameOver(), "Game should be over – black is in checkmate.");
        assertEquals("White won – mate!", game.getResultMessage());
    }
}
