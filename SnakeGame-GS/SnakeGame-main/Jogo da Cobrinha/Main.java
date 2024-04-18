import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameController gameController = new GameController();
            GameWindow gameWindow = new GameWindow(gameController);

            JFrame frame = new JFrame("Snake Game");
            frame.add(gameWindow);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            gameController.startGame();
        });
    }
}