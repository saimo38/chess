import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame extends JFrame {

    private Game game;
    private JLabel moveCountLabel;
    public MyFrame() {
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(700, 500));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel containerPanel = new JPanel(null);
        game = new Game();
        BoardPanel board = new BoardPanel(game);
        board.setMinimumSize(new Dimension(500, 500));
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(150, 40));
        newGameButton.setMaximumSize(new Dimension(150, 40));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setFocusPainted(false);

        moveCountLabel = new JLabel("Move Count: 0");
        moveCountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moveCountLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        newGameButton.addActionListener(e -> {
            Game newGame = new Game();
            board.setGame(newGame);
            board.upadteBoard();
            updateMoveCount();
        });

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(moveCountLabel);
        buttonPanel.add(Box.createVerticalGlue());

        add(buttonPanel, BorderLayout.EAST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void updateMoveCount() {
        moveCountLabel.setText("Move count: " + game.getMoveCount());
    }
}
