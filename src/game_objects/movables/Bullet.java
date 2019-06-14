package game_objects.movables;

import game_content.GameField;
import game_objects.Destructible;
import game_objects.map_objects.turf.Explosion;

public class Bullet extends Movable implements Destructible {

	/**
	 * Weird behaviour on big speeds, DON'T CHANGE
	 */
	private int speed = GameField.SCALE;
	public final static int WIDTH = 3 * GameField.SCALE;
	public final static int HEIGHT = 4 * GameField.SCALE;

	private Explosion explosion;

	public Bullet(int x, int y, Direction dir, int speed) {
		super(x, y, dir);
		this.speed *= speed;

		init();
		switch (currentDir) {
			case WEST:
				dx = -this.speed;
				break;
			case EAST:
				dx = this.speed;
				break;
			case NORTH:
				dy = -this.speed;
				break;
			case SOUTH:
				dy = this.speed;
				break;
		}
	}

	private void init() {
		loadImage("resources/sprites/bullet/bullet_%s.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {
		setVisible(false);
		int expX = getX() - GameField.BYTE;
		int expY = getY() - GameField.BYTE;
		if (currentDir == Direction.EAST || currentDir == Direction.SOUTH) {
			expX += getWidth();
			expY += getHeight();
		}
		explosion = new Explosion(PlayerTank.round(expX), PlayerTank.round(expY));
	}

	public Explosion getExplosion() {
		return explosion;
	}
}
