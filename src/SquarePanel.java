import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SquarePanel extends JPanel {

    private int row;
    private int col;
    private Color color;
    private Color oppositeColor;
    private JLabel topLeft;
    private JLabel bottomRight;

    private BufferedImage pieceImage;
    private Piece currentPiece;


    public SquarePanel(int row, int col) {
        this.row = row;
        this.col = col;
        setLayout(null);

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

        setBackground(color);
        setPreferredSize(new Dimension(64, 64));
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        resizeLabels();
        repaint();
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

    public void setPiece(Piece piece) {
        this.currentPiece = piece;

        if (piece != null) {
            int width = Math.max(getWidth(), 64);   // výchozí minimální velikost
            int height = Math.max(getHeight(), 64);
            pieceImage = SvgLoader.loadSvg(piece.getSvgPath(), width, height);
        } else {
            pieceImage = null;
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        if (currentPiece != null) {
            int width = getWidth();
            int height = getHeight();

            // Pokud obrázek ještě není načten nebo má jinou velikost než aktuální
            if (pieceImage == null || pieceImage.getWidth() != width || pieceImage.getHeight() != height) {
                pieceImage = SvgLoader.loadSvg(currentPiece.getSvgPath(), width, height);
            }

            if (pieceImage != null) {
                g2d.drawImage(pieceImage, 0, 0, width, height, null);
            }
        }

        g2d.dispose();
    }

    /*public void setPiece(Piece piece) {
        removeAll();
        currentPiece = piece;

        if (piece != null) {
            pieceLabel = new JLabel();
            add(pieceLabel);
        }
        resizeLabels();
        revalidate();
        repaint();
    }*/

    /*public void resizePiece(){
        if (currentPiece != null && pieceLabel != null) {
            int w = getWidth();
            int h = getHeight();

            if (w > 0 && h > 0) {
                Image img = currentPiece.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                pieceLabel.setIcon(new ImageIcon(img));
                pieceLabel.setBounds(0, 0, w, h);
            }
        }
    }*/

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
