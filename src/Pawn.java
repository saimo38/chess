import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece {

    //private final Image whiteImage = new ImageIcon(getClass().getResource("/images/pawn_white.png")).getImage();
    //private final Image blackImage = new ImageIcon(getClass().getResource("/images/pawn_black.png")).getImage();


    public Pawn(boolean white) {
        super(white);
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
