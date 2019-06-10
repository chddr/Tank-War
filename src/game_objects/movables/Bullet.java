package game_objects.movables;

import game_content.GameField;
import game_objects.Destructible;
import game_objects.map_objects.turf.Explosion;

public class Bullet extends Movable implements Destructible {

	private final static int SPEED = 5*GameField.SCALE;
	public final static int WIDTH = 3 * GameField.SCALE;
	public final static int HEIGHT = 4 * GameField.SCALE;

	private Explosion explosion;

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
		setVisible(false);
		explosion = new Explosion(Tank.round(getX()-GameField.BYTE/2, GameField.BYTE), Tank.round(getY()-GameField.BYTE/2, GameField.BYTE));
	}

	public Explosion getExplosion() {
		return explosion;
	}
}
