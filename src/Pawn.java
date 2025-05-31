import java.util.ArrayList;

public class Pawn extends Piece {

    //private final Image whiteImage = new ImageIcon(getClass().getResource("/images/pawn_white.png")).getImage();
    //private final Image blackImage = new ImageIcon(getClass().getResource("/images/pawn_black.png")).getImage();


    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public Piece clone() {
        return new Pawn(isWhite());
    }

    @Override
    public ArrayList<Move> getLegalMoves(int row, int col, ChessBoard board) {
        ArrayList<Move> legalMoves = new ArrayList<>();
        int direction = isWhite() ? -1 : 1;
        int startRow = isWhite() ? 6 : 1;

        int oneStepRow = row + direction;
        if (oneStepRow >= 0 && oneStepRow < 8 && board.getPiece(oneStepRow, col) == null) {
            legalMoves.add(new Move(row, col, oneStepRow, col));

            int twoStepRow = row + 2 * direction;
            if (row == startRow && board.getPiece(twoStepRow, col) == null) {
                legalMoves.add(new Move(row, col, twoStepRow, col));
            }
        }

        int rightCol = col + 1;
        if (oneStepRow >= 0 && oneStepRow < 8 && rightCol < 8) {
            Piece p = board.getPiece(oneStepRow, rightCol);
            if(p != null && p.isWhite() != isWhite()) {
                legalMoves.add(new Move(row, col, oneStepRow, rightCol));
            }
        }

        int leftCol = col - 1;
        if (oneStepRow >= 0 && oneStepRow < 8 && leftCol >= 0) {
            Piece p = board.getPiece(oneStepRow, leftCol);
            if(p != null && p.isWhite() != isWhite()) {
                legalMoves.add(new Move(row, col, oneStepRow, leftCol));
            }
        }

        return legalMoves;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_pawn.svg" : "/images/black_pawn.svg";
    }
}
