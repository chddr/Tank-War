package game_objects;

import game_content.GameField;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	protected BufferedImage image;

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
		try {
			image = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = scale(image);
	}

	/**
	 * scale image
	 *
	 * @param sbi image to scale
	 * @return scaled image
	 */
	public static BufferedImage scale(BufferedImage sbi) {
		int sc = GameField.SCALE;
		BufferedImage dbi = null;
		if (sbi != null) {
			dbi = new BufferedImage(sbi.getWidth() * sc, sbi.getHeight() * sc, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(sc, sc);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	protected void setX(int x) {
		this.x = x;
	}

	protected void setY(int y) {
		this.y = y;
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
