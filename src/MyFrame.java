import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    JPanel panel = new JPanel();

    private JButton[][] buttons = new JButton[8][8];

    public MyFrame(){
        panel.setLayout(new GridLayout(8,8));
        panel.setPreferredSize(new Dimension(500,500));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(500,500));
        this.setTitle("Chess");
        this.add(panel);

        this.setVisible(true);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                JButton button = new JButton();
                button.setFocusable(false);
                if (j % 2 == 0){
                    button.setBackground(Color.LIGHT_GRAY);
                } else {
                    button.setBackground(Color.DARK_GRAY);
                }
                panel.add(button);
            }
            
        }
    }


}
