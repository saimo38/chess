import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public ArrayList<Move> getLegalMoves(int row, int col, ChessBoard board) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        int[][] directions = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];

            if (r >= 0 && r < 8 && c >= 0 && c < 8){
                Piece p = board.getPiece(r, c);
                if (p == null || p.isWhite() != isWhite() && !(p instanceof King)){
                    legalMoves.add(new Move(row, col, r, c));
                }
            }
        }

        return legalMoves;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_knight.svg" : "/images/black_knight.svg";
    }

    @Override
    public String getType() {
        return "Knight";
    }
}
