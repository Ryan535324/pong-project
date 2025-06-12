javaCopyEdit;
import static utils.Constants.*;



public class Ball extends Sprite {
    private static final String BALL_IMAGE_PATH = ;
    private double vx;
    private double vy;

    public Ball() {
        super(BALL_IMAGE_PATH, 0, 0, BALL_SIZE, BALL_SIZE);
        reset();
    }

    public void reset() {
    }

    @Override
    public void tick() {
    }
}