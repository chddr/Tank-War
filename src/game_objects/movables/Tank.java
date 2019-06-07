package game_objects.movables;

import game_objects.Destructible;
import game_objects.Sprite;

import java.awt.event.KeyEvent;

public class Tank extends Sprite implements Destructible{

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

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}
}
