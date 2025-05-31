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

            if (r >= 0 && r < 8 && c >= 0 && c < 8) {
                Piece p = board.getPiece(r, c);
                if (p == null || p.isWhite() != isWhite()) {
                    legalMoves.add(new Move(row, col, r, c));
                }
            }
        }

        if (!this.isHasMoved()) {
            if (canCastleKingSide(row, col, board)) {
                Move castleMove = new Move(row, col, row, col + 2);
                castleMove.setCastleKingSide(true);
                legalMoves.add(castleMove);
            }

            if (canCastleQueenSide(row, col, board)) {
                Move castleMove = new Move(row, col, row, col - 2);
                castleMove.setCastleQueenSide(true);
                legalMoves.add(castleMove);
            }
        }
        return legalMoves;
    }

    private boolean canCastleKingSide(int row, int col, ChessBoard board) {
        Piece rook = board.getPiece(row, 7);
        if (!(rook instanceof Rook) || rook.isWhite() != this.isWhite() || rook.isHasMoved()) {
            return false;
        }
        if (board.getPiece(row, 5) != null || board.getPiece(row, 6) != null) {
            return false;
        }
        if (board.getGame().isKingInCheck(this.isWhite(), board)) {
            return false;
        }
        if (board.getGame().isSquareAttacked(row, 5, !this.isWhite(), board)) return false;
        if (board.getGame().isSquareAttacked(row, 6, !this.isWhite(), board)) return false;

        return true;
    }

    private boolean canCastleQueenSide(int row, int col, ChessBoard board) {
        Piece rook = board.getPiece(row, 0);
        if (!(rook instanceof Rook) || rook.isWhite() != this.isWhite() || rook.isHasMoved()) {
            return false;
        }
        if (board.getPiece(row, 1) != null || board.getPiece(row, 2) != null || board.getPiece(row, 3) != null) {
            return false;
        }
        if (board.getGame().isKingInCheck(this.isWhite(), board)) {
            return false;
        }
        if (board.getGame().isSquareAttacked(row, 2, !this.isWhite(), board)) return false;
        if (board.getGame().isSquareAttacked(row, 3, !this.isWhite(), board)) return false;

        return true;
    }

    @Override
    public String getSvgPath() {
        return white ? "/images/white_king.svg" : "/images/black_king.svg";
    }
}
