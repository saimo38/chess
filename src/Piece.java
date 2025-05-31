import java.util.ArrayList;

public abstract class Piece {
    protected boolean white;

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
