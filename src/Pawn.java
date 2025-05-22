import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece {

    private final Image whiteImage = new ImageIcon("pawn_white.png").getImage();
    private final Image blackImage = new ImageIcon("pawn_black.png").getImage();


    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public Image getImage() {
        return white ? whiteImage : blackImage;
    }

    @Override
    public String getType() {
        return "Pawn";
    }
}
