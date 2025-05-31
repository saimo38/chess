import java.util.ArrayList;

public class Game {
    private ChessBoard chessBoard;
    private boolean whiteTurn;
    private Move lastMove;
    private boolean gameOver = false;
    private String resultMessage = "";
    private int moveCount = 0;

    public boolean isKingInCheck(boolean white, ChessBoard board) {
        int kingRow = -1, kingCol = -1;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = board.getPiece(row, col);
                if (p instanceof King && p.isWhite() == white) {
                    kingRow = row;
                    kingCol = col;
                    break;
                }
            }
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = board.getPiece(row, col);
                if (p != null && p.isWhite() != white) {
                    for (Move m : p.getLegalMoves(row, col, board)){
                        if (m.getToRow() == kingRow && m.getToCol() == kingCol) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public ArrayList<Move> getLegalMovesSafe(int row, int col, ChessBoard board) {
        Piece piece = board.getPiece(row, col);
        if (piece == null || piece.isWhite() != whiteTurn) {
            return new ArrayList<>();
        }

        ArrayList<Move> allMoves = piece.getLegalMoves(row, col, board);
        ArrayList<Move> safeMoves = new ArrayList<>();

        for (Move move : allMoves) {
            ChessBoard copy = board.copy();

            Piece pieceCopy = copy.getPiece(move.getFromRow(), move.getFromCol());
            copy.setPiece(move.getToRow(), move.getToCol(), pieceCopy);
            copy.setPiece(move.getFromRow(), move.getFromCol(), null);

            if (!isKingInCheck(whiteTurn, copy)) {
                safeMoves.add(move);
            }
        }

        return safeMoves;
    }

    public void checkEndConditions() {
        boolean anySafeMove = false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = chessBoard.getPiece(row, col);
                if (piece != null && piece.isWhite() == whiteTurn) {
                    ArrayList<Move> safeMoves = getLegalMovesSafe(row, col, chessBoard);
                    if (!safeMoves.isEmpty()) {
                        anySafeMove = true;
                        break;
                    }
                }
            }
        }

        if (!anySafeMove) {
            gameOver = true;
            if (isKingInCheck(whiteTurn, chessBoard)) {
                resultMessage = whiteTurn ? "Black won – mate!" : "White won – mate!";
            } else {
                resultMessage = "Draw – stalemate!";
            }
        }
    }

    public boolean isSquareAttacked(int row, int col, boolean byWhite, ChessBoard board) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null && p.isWhite() == byWhite) {
                    for (Move m : p.getLegalMoves(r, c, board)) {
                        if (m.getToRow() == row && m.getToCol() == col) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public String getResultMessage() {
        return resultMessage;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Game(){
        chessBoard = new ChessBoard(false);
        chessBoard.setGame(this);
        whiteTurn = true;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void switchTurn(){
        whiteTurn = !whiteTurn;
        moveCount++;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
