public class ChessBoard {
    private Piece[][] chessBoard;

    public ChessBoard(boolean empty) {
        chessBoard = new Piece[8][8];
        if (!empty) {
            setUpInitialPosition();
        }
    }


    public Piece getPiece(int row, int col) {
        return chessBoard[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        chessBoard[row][col] = piece;
    }

    private void setUpInitialPosition(){
        chessBoard[7][0] = new Rook(true);
        chessBoard[7][1] = new Knight(true);
        chessBoard[7][2] = new Bishop(true);
        chessBoard[7][3] = new Queen(true);
        chessBoard[7][4] = new King(true);
        chessBoard[7][5] = new Bishop(true);
        chessBoard[7][6] = new Knight(true);
        chessBoard[7][7] = new Rook(true);
        for (int i = 0; i < 8; i++) {
            chessBoard[6][i] = new Pawn(true);
        }

        chessBoard[0][0] = new Rook(false);
        chessBoard[0][1] = new Knight(false);
        chessBoard[0][2] = new Bishop(false);
        chessBoard[0][3] = new Queen(false);
        chessBoard[0][4] = new King(false);
        chessBoard[0][5] = new Bishop(false);
        chessBoard[0][6] = new Knight(false);
        chessBoard[0][7] = new Rook(false);
        for (int i = 0; i < 8; i++) {
            chessBoard[1][i] = new Pawn(false);
        }
    }

    public ChessBoard copy() {
        ChessBoard newBoard = new ChessBoard(true);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece original = this.getPiece(row, col);
                if (original != null) {
                    newBoard.setPiece(row, col, original.clone());
                }
            }
        }
        return newBoard;
    }


    public Piece[][] getBoard() {
        return chessBoard;
    }
}
