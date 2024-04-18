import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.util.ArrayList;

public class GameController implements ActionListener {
    private Snake snake;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int UNIT_SIZE = 25;
    private Timer timer;
    private boolean isMovingRight = true;
    private boolean isMovingLeft = false;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isGameOver = false;

    public GameController() {
        snake = new Snake(WIDTH, HEIGHT, UNIT_SIZE);
        timer = new Timer(100, this);
        isGameOver = false;
    }

    public void startGame() {
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            snake.move(isMovingRight, isMovingLeft, isMovingUp, isMovingDown);
            if (snake.checkCollision()) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        isGameOver = true;
        timer.stop();
        int choice = JOptionPane.showConfirmDialog(null, "Game Over! Do you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        snake = new Snake(WIDTH, HEIGHT, UNIT_SIZE);
        isGameOver = false;
        isMovingRight = true;
        isMovingLeft = false;
        isMovingUp = false;
        isMovingDown = false;
        timer.start();
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public boolean isMovingUp() {
        return isMovingUp;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void setMovingDirection(boolean right, boolean left, boolean up, boolean down) {
        isMovingRight = right;
        isMovingLeft = left;
        isMovingUp = up;
        isMovingDown = down;
    }

    public ArrayList<Point> getSnakeBody() {
        return snake.getSnakeBody();
    }

    public Point getFood() {
        return snake.getFood();
    }

    public int getUnitSize() {
        return UNIT_SIZE;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}