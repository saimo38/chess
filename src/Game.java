public class Game {
    private ChessBoard chessBoard;
    private boolean whiteTurn;
    private Move lastMove;

    public Game(){
        chessBoard = new ChessBoard();
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
    }
}
