import static utils.Constants.*;
import java.util.Random;

public class Ball extends Sprite { ;
    private double vx;
    private double vy;
    private final Random rand = new Random();
    private double speedMultiplier = 1.0;

    public Ball() {
        super(BALL_IMAGE_PATH, 0, 0, BALL_SIZE, BALL_SIZE);
        reset();
    }

    @Override
    public void tick() {
        pos.x += vx;
        pos.y += vy;
    }

    public void reset() {
        reset(BOARD_WIDTH / 2 - BALL_SIZE / 2, BOARD_HEIGHT / 2 - BALL_SIZE / 2);
    }

    public void reset(int x, int y) {
        pos.x = x;
        pos.y = y;
        speedMultiplier = 1.0;
        double speed = BALL_INITIAL_SPEED * speedMultiplier;
        vx = rand.nextBoolean() ? speed : -speed;
        vy = rand.nextBoolean() ? speed : -speed;
    }

    public void reverseX() {
        speedMultiplier += 0.1;
        vx = -vx * speedMultiplier;
    }

    public void reverseY() {
        vy = -vy;
    }

    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public void setVx(double vx) { this.vx = vx; }
    public void setVy(double vy) { this.vy = vy; }
}