import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
                squares[row][col].highlightLegal(false);
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
        if (selectedRow == -1) {
            if (clickedPiece != null && clickedPiece.isWhite() == game.isWhiteTurn()) {
                selectedRow = row;
                selectedCol = col;
                squares[row][col].highlight(true);
                ArrayList<Move> legalMoves = clickedPiece.getLegalMoves(row, col, game.getChessBoard());
                for (Move move : legalMoves) {
                    squares[move.getToRow()][move.getToCol()].highlightLegal(true);
                }
            }
            return;
        }
        Piece selectedPiece = game.getChessBoard().getPiece(selectedRow, selectedCol);

        if (clickedPiece != null && clickedPiece.isWhite() == selectedPiece.isWhite()) {
            upadteBoard();
            selectedRow = row;
            selectedCol = col;
            squares[row][col].highlight(true);
            ArrayList<Move> legalMoves = clickedPiece.getLegalMoves(row, col, game.getChessBoard());
            for (Move move : legalMoves) {
                squares[move.getToRow()][move.getToCol()].highlightLegal(true);
            }
            return;
        }

        ArrayList<Move> legalMoves = selectedPiece.getLegalMoves(selectedRow, selectedCol, game.getChessBoard());
        boolean isLegal = false;
        for (Move move : legalMoves) {
            if (move.getToRow() == row && move.getToCol() == col) {
                isLegal = true;
                break;
            }
        }

        if (isLegal) {
            game.getChessBoard().setPiece(row, col, selectedPiece);
            game.getChessBoard().setPiece(selectedRow, selectedCol, null);
            game.setLastMove(new Move(selectedRow, selectedCol, row, col));
            game.switchTurn();

        }

        selectedRow = -1;
        selectedCol = -1;
        upadteBoard();
    }

    /*public void handleSquareClick(int row, int col) {
        Piece clickedPiece = game.getChessBoard().getPiece(row, col);

        if (selectedRow == -1) {
            // Výběr figurky
            if (clickedPiece != null && clickedPiece.isWhite() == game.isWhiteTurn()) {
                selectedRow = row;
                selectedCol = col;
                squares[row][col].highlight(true);
            }
            return;
        }

        Piece selectedPiece = game.getChessBoard().getPiece(selectedRow, selectedCol);

        // Pokud hráč klikne na jinou vlastní figurku
        if (clickedPiece != null && clickedPiece.isWhite() == selectedPiece.isWhite()) {
            // Přepne výběr na novou figurku
            upadteBoard();  // smaže předchozí zvýraznění
            selectedRow = row;
            selectedCol = col;
            squares[row][col].highlight(true);
            return;
        }

        // Zkontroluj, jestli tah je mezi povolenými
        ArrayList<Move> legalMoves = selectedPiece.getLegalMoves(selectedRow, selectedCol, game.getChessBoard());
        boolean isLegal = legalMoves.stream().anyMatch(m -> m.getToRow() == row && m.getToCol() == col);

        if (isLegal) {
            // Proveď tah
            game.getChessBoard().setPiece(row, col, selectedPiece);
            game.getChessBoard().setPiece(selectedRow, selectedCol, null);
            game.setLastMove(new Move(row, col, selectedRow, selectedCol));
            game.switchTurn();
        }

        selectedRow = -1;
        selectedCol = -1;
        upadteBoard();  // Aktualizace zobrazení
    }*/


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
