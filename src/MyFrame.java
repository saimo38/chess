import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame extends JFrame {

    public MyFrame() {
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel containerPanel = new JPanel(null);
        Game game = new Game();
        BoardPanel board = new BoardPanel(game);
        containerPanel.add(board);
        add(containerPanel, BorderLayout.CENTER);
        containerPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int size = Math.min(containerPanel.getWidth(), containerPanel.getHeight());
                board.setBounds(0, 0, size, size);
                board.revalidate();
                board.repaint();
            }
        });

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            Game newGame = new Game();
            board.setGame(newGame);
            board.upadteBoard();
        });
        add(newGameButton, BorderLayout.EAST);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
