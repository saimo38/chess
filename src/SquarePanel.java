import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    private int row;
    private int col;
    private Color color;
    private Color oppositeColor;
    private JLabel topLeft;
    private JLabel bottomRight;

    private JPanel piecePanel;
    private Piece currentPiece;


    public SquarePanel(int row, int col) {
        this.row = row;
        this.col = col;

        if ((row + col) % 2 == 0) {
            color = new Color(240, 217, 181);
            oppositeColor = new Color(181, 136, 99);
        } else {
            color = new Color(181, 136, 99);
            oppositeColor = new Color(240, 217, 181);
        }

        if (col == 0) {
            topLeft = new JLabel(String.valueOf(8 - row));
            topLeft.setForeground(oppositeColor);
            topLeft.setBounds(2, 2, 20, 20);
            add(topLeft);
        }

        if (row == 7) {
            char letter = (char) ('a' + col);
            bottomRight = new JLabel(String.valueOf(letter));
            bottomRight.setForeground(oppositeColor);
            bottomRight.setBounds(42, 42, 20, 20);
            add(bottomRight);
        }

        setLayout(null);
        setBackground(color);
        setPreferredSize(new Dimension(64, 64));
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        resizeLabels();
    }

    public void resizeLabels() {
        int width = getWidth();
        int height = getHeight();

        int fontSize = height / 5;
        Font font = new Font("Arial", Font.BOLD, fontSize);
        FontMetrics metrics = getFontMetrics(font);

        if (topLeft != null) {
            topLeft.setFont(font);
            int textW = metrics.stringWidth(topLeft.getText());
            int textH = metrics.getHeight();
            topLeft.setBounds(4, 2, textW, textH);
        }

        if (bottomRight != null) {
            bottomRight.setFont(font);
            int textW = metrics.stringWidth(bottomRight.getText());
            int textH = metrics.getHeight();
            bottomRight.setBounds(width - textW - 4, height - textH - 2, textW, textH);
        }

        repaint();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
