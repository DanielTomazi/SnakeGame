import java.awt.*;
import java.util.Random;

public class Food {
    private Point food;
    private final int UNIT_SIZE;

    public Food(int width, int height, int unitSize) {
        UNIT_SIZE = unitSize;
        generateFood(width, height);
    }

    public Point getFoodPosition() {
        return food;
    }

    public void generateFood(int width, int height) {
        Random random = new Random();
        int foodX = random.nextInt(width / UNIT_SIZE) * UNIT_SIZE;
        int foodY = random.nextInt(height / UNIT_SIZE) * UNIT_SIZE;
        food = new Point(foodX, foodY);
    }
}