import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //setMinimumSize(new Dimension(800, 800));

        BoardPanel board = new BoardPanel();
        add(board, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        /*panel.setLayout(new GridLayout(8,8));
        panel.setPreferredSize(new Dimension(600,600));
        panel.setBorder(null);
        row.setPreferredSize(new Dimension(100,800));
        row.setLayout(new GridLayout(8,1));
        for (int i = 0; i < pannels.length; i++) {
            JLabel l = new JLabel(String.valueOf(pannels.length - i));
            l.setHorizontalAlignment(JLabel.CENTER);
            row.add(l);
        }
        col.setBackground(Color.GREEN);
        col.setPreferredSize(new Dimension(800,100));

        this.setTitle("Chess");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(600,600));
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(col, BorderLayout.NORTH);
        this.add(row, BorderLayout.WEST);
        this.setVisible(true);

        for (int i = 0; i < pannels.length; i++) {
            for (int j = 0; j < pannels[i].length; j++) {
                JPanel tile = new JPanel();
                //tile.setFocusable(false);
                //tile.setBorder(null);
                if ((i+j) % 2 == 0){
                    tile.setBackground(new Color(240,217,181));
                } else {
                    tile.setBackground(new Color(181,136,99));
                }
                panel.add(tile);
            }
        }
        panel.revalidate();*/
    }
}
