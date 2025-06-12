import java.util.Set;
import static utils.Constants.*;

public abstract class Paddle extends Sprite {
    protected int dy;

    public Paddle(int x, int y) {
        super(PADDLE_IMAGE_PATH, x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    @Override
    public void tick() {
        pos.y += dy;
        pos.y = Math.max(0, Math.min(pos.y, BOARD_HEIGHT - PADDLE_HEIGHT));
    }

    public abstract void handleKeys(Set<Integer> activeKeys);
}
