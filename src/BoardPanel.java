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
                squares[row][col].highlightKingCheck(false);
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

        boolean whiteInCheck = game.isKingInCheck(true, game.getChessBoard());
        boolean blackInCheck = game.isKingInCheck(false, game.getChessBoard());

        if (whiteInCheck) {
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    Piece p = board[r][c];
                    if (p instanceof King && p.isWhite()) {
                        squares[r][c].highlightKingCheck(true);
                        break;
                    }
                }
            }
        }

        if (blackInCheck) {
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    Piece p = board[r][c];
                    if (p instanceof King && !p.isWhite()) {
                        squares[r][c].highlightKingCheck(true);
                        break;
                    }
                }
            }
        }
        repaint();
    }

    public void handleSquareClick(int row, int col) {
        if (game.isGameOver()) {
            return;
        }

        Piece clickedPiece = game.getChessBoard().getPiece(row, col);
        if (selectedRow == -1) {
            if (clickedPiece != null && clickedPiece.isWhite() == game.isWhiteTurn()) {
                selectedRow = row;
                selectedCol = col;
                squares[row][col].highlight(true);
                ArrayList<Move> legalMoves = game.getLegalMovesSafe(row, col, game.getChessBoard());
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
            ArrayList<Move> legalMoves = game.getLegalMovesSafe(row, col, game.getChessBoard());
            for (Move move : legalMoves) {
                squares[move.getToRow()][move.getToCol()].highlightLegal(true);
            }
            return;
        }

        ArrayList<Move> legalMoves = game.getLegalMovesSafe(selectedRow, selectedCol, game.getChessBoard());
        boolean isLegal = false;
        for (Move move : legalMoves) {
            if (move.getToRow() == row && move.getToCol() == col) {
                isLegal = true;
                break;
            }
        }

        if (isLegal) {
            Move actualMove = null;
            for (Move move : legalMoves) {
                if (move.getToRow() == row && move.getToCol() == col) {
                    actualMove = move;
                    break;
                }
            }

            game.getChessBoard().setPiece(row, col, selectedPiece);
            game.getChessBoard().setPiece(selectedRow, selectedCol, null);

            if (actualMove != null) {
                if (actualMove.isCastleKingSide()) {
                    game.getChessBoard().setPiece(row, col, selectedPiece);
                    game.getChessBoard().setPiece(selectedRow, selectedCol, null);

                    Piece rook = game.getChessBoard().getPiece(selectedRow, 7);
                    game.getChessBoard().setPiece(selectedRow, 5, rook);
                    game.getChessBoard().setPiece(selectedRow, 7, null);

                    selectedPiece.setHasMoved(true);
                    rook.setHasMoved(true);

                } else if (actualMove.isCastleQueenSide()) {
                    game.getChessBoard().setPiece(row, col, selectedPiece);
                    game.getChessBoard().setPiece(selectedRow, selectedCol, null);

                    Piece rook = game.getChessBoard().getPiece(selectedRow, 0);
                    game.getChessBoard().setPiece(selectedRow, 3, rook);
                    game.getChessBoard().setPiece(selectedRow, 0, null);

                    selectedPiece.setHasMoved(true);
                    rook.setHasMoved(true);

                } else {
                    game.getChessBoard().setPiece(row, col, selectedPiece);
                    game.getChessBoard().setPiece(selectedRow, selectedCol, null);

                    if (actualMove.isEnPassant()) {
                        int direction = selectedPiece.isWhite() ? 1 : -1;
                        game.getChessBoard().setPiece(row + direction, col, null);
                    }
                }
            }

            if (selectedPiece instanceof Pawn) {
                if ((selectedPiece.isWhite() && row == 0) || (!selectedPiece.isWhite() && row == 7)) {
                    String[] options = {"Queen", "Rook", "Bishop", "Knight"};
                    int choice = JOptionPane.showOptionDialog(null, "Promote to:", "Promotion", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    Piece newPiece = switch (choice) {
                        case 1 -> new Rook(selectedPiece.isWhite());
                        case 2 -> new Bishop(selectedPiece.isWhite());
                        case 3 -> new Knight(selectedPiece.isWhite());
                        default -> new Queen(selectedPiece.isWhite());
                    };

                    game.getChessBoard().setPiece(row, col, newPiece);
                }
            }

            game.setLastMove(actualMove);
            game.switchTurn();
            game.checkEndConditions();

            if (game.isGameOver()) {
                upadteBoard();
                JOptionPane.showMessageDialog(null, game.getResultMessage(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        selectedRow = -1;
        selectedCol = -1;
        upadteBoard();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Dimension getPreferredSize() {
        int size = Math.min(getParent().getWidth(), getParent().getHeight());
        return new Dimension(size, size);
    }
}
