import java.awt.event.KeyEvent;
import java.util.Set;
import static utils.Constants.*;

public class PaddlePlayer1 extends Paddle {
    public PaddlePlayer1(int x, int y) {
        super(x, y);
    }
    @Override
    public void handleKeys(Set<Integer> activeKeys) {
        dy = 0;
        if (activeKeys.contains(KeyEvent.VK_W)) dy -= PADDLE_SPEED;
        if (activeKeys.contains(KeyEvent.VK_S)) dy += PADDLE_SPEED;
    }
}
