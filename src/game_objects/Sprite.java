package game_objects;

import javax.imageio.ImageIO;
import java.awt.*;

public class Sprite {
	private int x;
	private int y;
	private int width;
	private int height;
	protected boolean visible;
	private Image image;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
	}

	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	protected void loadImage(String imageName) {
		image = Toolkit.getDefaultToolkit().createImage(imageName);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
