import java.util.ArrayList;

public class Pawn extends Piece {

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

        for (int dCol : new int[]{-1, 1}) {
            int targetCol = col + dCol;
            if (isValidSquare(oneStepRow, targetCol)) {
                Piece targetPiece = board.getPiece(oneStepRow, targetCol);

                if (targetPiece != null && targetPiece.isWhite() != isWhite()) {
                    legalMoves.add(new Move(row, col, oneStepRow, targetCol));
                }

                Move lastMove = board.getGame().getLastMove();
                if (lastMove != null) {
                    int fromRow = lastMove.getFromRow();
                    int toRow = lastMove.getToRow();
                    int toCol = lastMove.getToCol();

                    Piece movedPiece = board.getPiece(toRow, toCol);
                    if (movedPiece instanceof Pawn
                            && movedPiece.isWhite() != isWhite()
                            && Math.abs(toRow - fromRow) == 2
                            && toRow == row
                            && toCol == targetCol) {
                        Move enPassantMove = new Move(row, col, oneStepRow, targetCol);
                        enPassantMove.setEnPassant(true);
                        legalMoves.add(enPassantMove);
                    }
                }
            }
        }

        return legalMoves;
    }

    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_pawn.svg" : "/images/black_pawn.svg";
    }
}
