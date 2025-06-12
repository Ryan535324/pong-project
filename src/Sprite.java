import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public abstract class Sprite {
    protected Point pos;
    protected Dimension size;
    private BufferedImage image;

    public Sprite(String imagePath, int x, int y, int width, int height) {
        pos = new Point(x, y);
        loadImage(imagePath);
        size = new Dimension(width, height);
    }

    private void loadImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            System.err.println("Failed to load image: " + imagePath);
        }
    }

    public void draw(Graphics graphics, ImageObserver observer) {
        graphics.drawImage(image, pos.x, pos.y, size.width, size.height, observer);
    }

    public abstract void tick();
}
