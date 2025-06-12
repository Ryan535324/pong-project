import java.awt.event.KeyEvent;
import java.util.Set;
import static utils.Constants.*;

public class PaddlePlayer2 extends Paddle {
    public PaddlePlayer2(int x, int y) {
        super(x, y);
    }
    @Override
    public void handleKeys(Set<Integer> activeKeys) {
        dy = 0;
        if (activeKeys.contains(KeyEvent.VK_UP)) dy -= PADDLE_SPEED;
        if (activeKeys.contains(KeyEvent.VK_DOWN)) dy += PADDLE_SPEED;
    }
}
