package game_objects.movables;

import game_objects.Destructible;
import game_objects.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank extends Sprite implements Destructible {

	/**
	 * Directions images in such an order: west, east, north, south
	 */
	private Image[] directions;
	private int dx, dy;

	public Tank(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		directions = new Image[4];
		for (int i = 0; i < 4; i++) {
			String dir;
			switch (i) {
				case 0:
					dir = "west";
					break;
				case 1:
					dir = "east";
					break;
				case 2:
					dir = "north";
					break;
				default:
					dir = "south";
			}
			String str = String.format("resources/sprites/player_tank/tank_%s.png", dir);
			directions[i] = Toolkit.getDefaultToolkit().createImage(str);
		}
		image = directions[2];
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		dx = 0;
		dy = 0;

		if (key == KeyEvent.VK_LEFT) {
			image = directions[0];
			dx = -1;
		}

		if (key == KeyEvent.VK_RIGHT) {
			image = directions[1];
			dx = 1;
		}

		if (key == KeyEvent.VK_UP) {
			image = directions[2];
			dy = -1;
		}

		if (key == KeyEvent.VK_DOWN) {
			image = directions[3];
			dy = 1;
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

	public void move() {
		move(dx,dy);
	}

}
