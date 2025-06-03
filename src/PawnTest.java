import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PawnTest {

    @Test
    void testEnPassantAvailable() {
        Game game = new Game();
        ChessBoard board = game.getChessBoard();

        Pawn whitePawn = new Pawn(true);
        Pawn blackPawn = new Pawn(false);

        board.setPiece(3, 4, whitePawn);
        board.setPiece(1, 5, blackPawn);

        board.setPiece(3, 5, blackPawn);
        board.setPiece(1, 5, null);
        Move lastMove = new Move(1, 5, 3, 5);
        game.setLastMove(lastMove);
        board.setGame(game);

        ArrayList<Move> moves = whitePawn.getLegalMoves(3, 4, board);
        boolean hasEnPassant = false;
        for (Move m : moves) {
            if (m.isEnPassant()) {
                hasEnPassant = true;
                break;
            }
        }

        assertTrue(hasEnPassant, "En passant should be available for white pawn.");
    }
}
