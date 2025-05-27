import java.awt.*;

public abstract class Piece {
    protected boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public abstract String getSvgPath();

    public abstract String getType();
}
