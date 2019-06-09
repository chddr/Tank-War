package game_objects.movables;

import game_content.GameField;
import game_objects.Destructible;

public class Bullet extends Movable implements Destructible {

	private final static int SPEED = 5;
	public final static int WIDTH = 3 * GameField.SCALE;
	public final static int HEIGHT = 4 * GameField.SCALE;

	public Bullet(int x, int y, Direction dir) {
		super(x, y, dir);

		init();
	}

	private void init() {
		loadImage("resources/sprites/bullet/bullet_%s.png");
		getImageDimensions();
	}

	public void move() {
		switch (currentDir) {
			case WEST:
				dx = -SPEED;
				break;
			case EAST:
				dx = SPEED;
				break;
			case NORTH:
				dy = -SPEED;
				break;
			case SOUTH:
				dy = SPEED;
				break;
		}
		super.move();
	}

	@Override
	public void destroy() {

	}
}
