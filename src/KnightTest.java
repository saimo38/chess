import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class KnightTest {

    @Test
    void testLegalMovesFromCenter() {
        ChessBoard board = new ChessBoard(true);
        Knight knight = new Knight(true);
        board.setPiece(4, 4, knight);

        ArrayList<Move> moves = knight.getLegalMoves(4, 4, board);
        assertEquals(8, moves.size(), "Knight should have 8 moves from center on empty board.");
    }

    @Test
    void testBlockedBySameColor() {
        ChessBoard board = new ChessBoard(true);
        Knight knight = new Knight(true);
        board.setPiece(4, 4, knight);
        board.setPiece(6, 5, new Pawn(true));

        ArrayList<Move> moves = knight.getLegalMoves(4, 4, board);
        boolean containsMove = false;
        for (Move m : moves) {
            if (m.getToRow() == 6 && m.getToCol() == 5) {
                containsMove = true;
                break;
            }
        }
        assertFalse(containsMove);
    }

    @Test
    void testCanCaptureEnemy() {
        ChessBoard board = new ChessBoard(true);
        Knight knight = new Knight(true);
        board.setPiece(4, 4, knight);
        board.setPiece(6, 5, new Pawn(false));

        ArrayList<Move> moves = knight.getLegalMoves(4, 4, board);
        boolean containsMove = false;
        for (Move m : moves) {
            if (m.getToRow() == 6 && m.getToCol() == 5) {
                containsMove = true;
                break;
            }
        }
        assertTrue(containsMove);
    }
}
