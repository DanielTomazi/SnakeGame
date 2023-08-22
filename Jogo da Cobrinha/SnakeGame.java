import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 25;
    private final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private final int DELAY = 100;
    private final ArrayList<Point> snakeBody = new ArrayList<>();
    private Point food;
    private boolean isMovingRight = true;
    private boolean isMovingLeft = false;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isGameOver = false;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_LEFT) && (!isMovingRight)) {
                    isMovingLeft = true;
                    isMovingUp = false;
                    isMovingDown = false;
                }
                if ((key == KeyEvent.VK_RIGHT) && (!isMovingLeft)) {
                    isMovingRight = true;
                    isMovingUp = false;
                    isMovingDown = false;
                }
                if ((key == KeyEvent.VK_UP) && (!isMovingDown)) {
                    isMovingUp = true;
                    isMovingRight = false;
                    isMovingLeft = false;
                }
                if ((key == KeyEvent.VK_DOWN) && (!isMovingUp)) {
                    isMovingDown = true;
                    isMovingRight = false;
                    isMovingLeft = false;
                }
            }
        });
        initGame();
    }

    public void initGame() {
        snakeBody.clear();
        snakeBody.add(new Point(WIDTH / 2, HEIGHT / 2));
        generateFood();
        isGameOver = false;
        isMovingRight = true;
        isMovingLeft = false;
        isMovingUp = false;
        isMovingDown = false;
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public void generateFood() {
        Random random = new Random();
        int foodX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int foodY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        food = new Point(foodX, foodY);
    }

    public void move() {
        Point newHead = new Point(snakeBody.get(0));
        if (isMovingRight) {
            newHead.x += UNIT_SIZE;
        }
        if (isMovingLeft) {
            newHead.x -= UNIT_SIZE;
        }
        if (isMovingUp) {
            newHead.y -= UNIT_SIZE;
        }
        if (isMovingDown) {
            newHead.y += UNIT_SIZE;
        }
        snakeBody.add(0, newHead);
        if (!snakeBody.get(0).equals(food)) {
            snakeBody.remove(snakeBody.size() - 1);
        } else {
            generateFood();
        }
    }

    public void checkCollision() {
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeBody.get(0).equals(snakeBody.get(i))) {
                isGameOver = true;
                break;
            }
        }
        if (snakeBody.get(0).x < 0 || snakeBody.get(0).x >= WIDTH || snakeBody.get(0).y < 0 || snakeBody.get(0).y >= HEIGHT) {
            isGameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            move();
            checkCollision();
            repaint();
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "Game Over! Do you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0);
            }
        }
    }

    public void restartGame() {
        initGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isGameOver) {
            for (Point bodyPart : snakeBody) {
                g.setColor(Color.GREEN);
                g.fillRect(bodyPart.x, bodyPart.y, UNIT_SIZE, UNIT_SIZE);
            }
            g.setColor(Color.RED);
            g.fillRect(food.x, food.y, UNIT_SIZE, UNIT_SIZE);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.drawString("Game Over", WIDTH / 2 - 120, HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            SnakeGame snakeGame = new SnakeGame();
            frame.add(snakeGame);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
