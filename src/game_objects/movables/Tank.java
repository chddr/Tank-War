package game_objects.movables;

import game_objects.Destructible;
import game_objects.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank extends Sprite implements Destructible {

	/**
	 * Directions images in such an order: west, east, north, south
	 */
	private BufferedImage[] directions;
	private int dx, dy;

	public Tank(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		directions = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			String dir = Direction.values()[i].toString().toLowerCase();
			String str = String.format("resources/sprites/player_tank/tank_%s.png", dir);
			try {
				directions[i] = ImageIO.read(new File(str));
			} catch (IOException e) {
				e.printStackTrace();
			}
			directions[i] = scale(directions[i]);
		}
		image = directions[2];
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}

	public void changeDirection(Direction dir) {
		dx = 0;
		dy = 0;

		switch (dir) {
			case WEST:
				image = directions[0];
				dx = -2;
				break;
			case EAST:
				image = directions[1];
				dx = 2;
				break;
			case NORTH:
				image = directions[2];
				dy = -2;
				break;
			case SOUTH:
				image = directions[3];
				dy = 2;
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	/**
	 * Method that moves the tank on the board. Should be used only when check for collision was already made.
	 */
	public void move() {
		setX(dx + getX());
		setY(dy + getY());
	}

}
