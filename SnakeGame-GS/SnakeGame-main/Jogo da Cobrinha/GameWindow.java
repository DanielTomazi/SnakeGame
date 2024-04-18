import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameWindow extends JPanel {
    private GameController gameController;

    public GameWindow(GameController controller) {
        this.gameController = controller;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_LEFT) && (!gameController.isMovingRight())) {
                    gameController.setMovingDirection(false, true, false, false);
                } else if ((key == KeyEvent.VK_RIGHT) && (!gameController.isMovingLeft())) {
                    gameController.setMovingDirection(true, false, false, false);
                } else if ((key == KeyEvent.VK_UP) && (!gameController.isMovingDown())) {
                    gameController.setMovingDirection(false, false, true, false);
                } else if ((key == KeyEvent.VK_DOWN) && (!gameController.isMovingUp())) {
                    gameController.setMovingDirection(false, false, false, true);
                }
            }
        });

        // Iniciar um loop para atualizar o painel do jogo
        new Thread(() -> {
            while (true) {
                repaint(); // Atualiza o painel do jogo
                try {
                    Thread.sleep(100); // Pausa para controlar a velocidade da cobra
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<Point> snakeBody = gameController.getSnakeBody();
        Point food = gameController.getFood();
        int unitSize = gameController.getUnitSize();

        // Desenha a cobra
        g.setColor(Color.GREEN);
        for (Point bodyPart : snakeBody) {
            g.fillRect(bodyPart.x, bodyPart.y, unitSize, unitSize);
        }

        // Desenha o alimento
        g.setColor(Color.RED);
        g.fillRect(food.x, food.y, unitSize, unitSize);

        // Verifica se houve colisão para encerrar o jogo
        if (gameController.isGameOver()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.drawString("Game Over", 200, 300);
        }
    }
}