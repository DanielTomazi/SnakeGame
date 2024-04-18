import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
    private ArrayList<Point> snakeBody;
    private final int UNIT_SIZE;
    private final int WIDTH;
    private final int HEIGHT;
    private Point food;

    public Snake(int width, int height, int unitSize) {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Point(width / 2, height / 2));
        UNIT_SIZE = unitSize;
        WIDTH = width;
        HEIGHT = height;
        generateFood();
    }

    public ArrayList<Point> getSnakeBody() {
        return snakeBody;
    }

    public void move(boolean isMovingRight, boolean isMovingLeft, boolean isMovingUp, boolean isMovingDown) {
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

    public boolean checkCollision() {
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeBody.get(0).equals(snakeBody.get(i))) {
                return true;
            }
        }
        return snakeBody.get(0).x < 0 || snakeBody.get(0).x >= WIDTH || snakeBody.get(0).y < 0 || snakeBody.get(0).y >= HEIGHT;
    }

    private void generateFood() {
        Random random = new Random();
        int foodX = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int foodY = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        food = new Point(foodX, foodY);
    }

    public Point getFood() {
        return food;
    }
}