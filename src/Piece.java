import java.util.ArrayList;

public abstract class Piece {
    protected boolean white;
    protected boolean hasMoved;

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public abstract ArrayList<Move> getLegalMoves(int row, int col, ChessBoard board);

    public abstract Piece clone();

    public abstract String getSvgPath();
}
