import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public Piece clone() {
        return new Queen(isWhite());
    }

    @Override
    public ArrayList<Move> getLegalMoves(int row, int col, ChessBoard board) {
        ArrayList<Move> legalMoves = new ArrayList<>();

        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] direction : directions) {
            int r = row + direction[0];
            int c = col + direction[1];

            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                Piece p = board.getPiece(r, c);
                if (p == null) {
                    legalMoves.add(new Move(row, col, r, c));
                } else {
                    if (p.isWhite() != isWhite()) {
                        legalMoves.add(new Move(row, col, r, c));
                    }
                    break;
                }
                r += direction[0];
                c += direction[1];
            }
        }

        return legalMoves;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_queen.svg" : "/images/black_queen.svg";
    }
}
