import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pawn extends Piece {

    //private final Image whiteImage = new ImageIcon(getClass().getResource("/images/pawn_white.png")).getImage();
    //private final Image blackImage = new ImageIcon(getClass().getResource("/images/pawn_black.png")).getImage();


    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Move> getLegalMoves(int row, int col, ChessBoard board) {
        return null;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_pawn.svg" : "/images/black_pawn.svg";
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}
