import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private SquarePanel[][] squares = new SquarePanel[8][8];
    private Game game;
    private int selectedRow = -1;
    private int selectedCol = -1;

    public BoardPanel(Game game) {
        this.game = game;
        setLayout(new GridLayout(8, 8));

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                SquarePanel square = new SquarePanel(row, col);
                squares[row][col] = square;
                add(square);
            }
        }

        upadteBoard();
    }

    public void upadteBoard() {
        Piece[][] board = game.getChessBoard().getBoard();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].highlight(false);
            }
        }

        Move lastMove = game.getLastMove();
        if (lastMove != null) {
            squares[lastMove.getFromRow()][lastMove.getFromCol()].highlight(true);
            squares[lastMove.getToRow()][lastMove.getToCol()].highlight(true);
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].setPiece(board[row][col]);
            }
        }

        repaint();
    }

    public void handleSquareClick(int row, int col) {
        Piece clickedPiece = game.getChessBoard().getPiece(row, col);
        if (selectedRow == -1 && clickedPiece != null && clickedPiece.isWhite() == game.isWhiteTurn()) {
            selectedRow = row;
            selectedCol = col;
            squares[row][col].highlight(true);
            return;
        }

        if (selectedRow != -1) {
            Piece selectedPiece = game.getChessBoard().getPiece(selectedRow, selectedCol);
            Move move = new Move(row, col, selectedRow, selectedCol);

            if (selectedPiece != null && (clickedPiece == null || clickedPiece.isWhite() != selectedPiece.isWhite())) {
                game.getChessBoard().setPiece(row, col, selectedPiece);
                game.getChessBoard().setPiece(selectedRow, selectedCol, null);
                squares[row][col].highlight(true);
                game.setLastMove(move);
                game.switchTurn();
            }

            selectedRow = -1;
            selectedCol = -1;
            upadteBoard();
        }

    }

    /*public void clearHighlight(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].highlight(false);
            }
        }
    }*/

    public SquarePanel getSquare(int row, int col) {
        return squares[row][col];
    }

    @Override
    public Dimension getPreferredSize() {
        int size = Math.min(getParent().getWidth(), getParent().getHeight());
        return new Dimension(size, size);
    }
}
