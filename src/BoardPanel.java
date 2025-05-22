import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private SquarePanel[][] squares = new SquarePanel[8][8];

    public BoardPanel() {
        setLayout(new GridLayout(8, 8));

        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                SquarePanel square = new SquarePanel(row, col);
                squares[row][col] = square;
                add(square);
            }
        }
    }

    public SquarePanel getSquare(int row, int col) {
        return squares[row][col];
    }

    @Override
    public Dimension getPreferredSize() {
        int size = Math.min(getParent().getWidth(), getParent().getHeight());
        return new Dimension(size, size);
    }
}
