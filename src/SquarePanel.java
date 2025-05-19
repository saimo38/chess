import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    private int row;
    private int col;
    private Color color;
    private Color oppositeColor;

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
            JLabel label = new JLabel(String.valueOf(8 - row));
            label.setForeground(oppositeColor);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            add(label);
        } else if (row == 7) {
            char letter = (char) ('a' + col);
            JLabel label = new JLabel(String.valueOf(letter));
            label.setForeground(oppositeColor);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            add(label);
        }

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(color);
        setPreferredSize(new Dimension(64, 64));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
