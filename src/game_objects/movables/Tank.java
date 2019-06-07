package game_objects.movables;

import game_objects.Destructible;
import game_objects.Sprite;

import java.awt.event.KeyEvent;

public class Tank extends Sprite implements Destructible {

	private int dx;
	private int dy;

	public Tank(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		loadImage("resources/sprites/player_tank/tank_north.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
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
