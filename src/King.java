import java.util.ArrayList;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public Piece clone() {
        return new King(isWhite());
    }

    @Override
    public ArrayList<Move> getLegalMoves(int row, int col, ChessBoard board) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];

            if (r >= 0 && r < 8 && c >= 0 && c < 8){
                Piece p = board.getPiece(r, c);
                if (p == null || p.isWhite() != isWhite()){
                    legalMoves.add(new Move(row, col, r, c));
                }
            }
        }

        return legalMoves;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_king.svg" : "/images/black_king.svg";
    }
}
