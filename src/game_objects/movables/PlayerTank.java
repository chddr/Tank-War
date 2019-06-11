package game_objects.movables;

import game_objects.Destructible;

import java.awt.event.KeyEvent;

public class PlayerTank extends Tank implements Destructible {

	public PlayerTank(int x, int y, Direction dir) {
		super(x, y, dir, 1500);

		init();
	}

	private void init() {
		loadImage("resources/sprites/player_tank/tank_%s.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

}
