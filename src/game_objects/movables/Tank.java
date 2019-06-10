package game_objects.movables;

import game_content.GameField;
import game_objects.Destructible;
import javafx.scene.transform.Scale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;

public class Tank extends Movable implements Destructible {

	private final static int SPEED = 1*GameField.SCALE;
	/**
	 * Delay between bullets (in milliseconds)
	 */
	private static final int DELAY = 1500;
	private ArrayList<Bullet> bullets;
	private long bulletTimer;

	public Tank(int x, int y, Direction dir) {
		super(x, y, dir);

		init();
	}

	private void init() {
		bullets = new ArrayList<>();
		loadImage("resources/sprites/player_tank/tank_%s.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}

	public void changeDirection(Direction dir) {
		//Slight move to fit the tank in the narrow corridors and other places
		if (Direction.isTurn(currentDir, dir)) {
			double coord;
			switch (currentDir) {
				case WEST:
				case EAST:
					setX(round(getX(),GameField.BYTE));
					break;
				case NORTH:
				case SOUTH:
					setY(round(getY(),GameField.BYTE));
					break;
			}
		}
		currentDir = dir;

		switch (dir) {
			case WEST:
				image = directions[0];
				dx = -SPEED;
				dy = 0;
				break;
			case EAST:
				image = directions[1];
				dx = SPEED;
				dy = 0;
				break;
			case NORTH:
				image = directions[2];
				dy = -SPEED;
				dx = 0;
				break;
			case SOUTH:
				image = directions[3];
				dy = SPEED;
				dx = 0;
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP|| key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	/**
	 * Get bullets that tank has shot
	 * @return bullets
	 */
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * Tank fires a bullet. It should be copied to GameField, where we can control their collision
	 */
	public void fire() {
		long timePassed = System.currentTimeMillis() - bulletTimer;
		int delay = bullets.isEmpty() ? DELAY/3 : DELAY;
		if(timePassed<delay)
			return;
		int x, y;
		switch (currentDir) {
			case WEST:
				x = getBounds().x - Bullet.HEIGHT;
				y = getBounds().y + (getBounds().height - Bullet.WIDTH) / 2;
				break;
			case EAST:
				x = getBounds().x + getBounds().width;
				y = getBounds().y + (getBounds().height - Bullet.WIDTH) / 2;
				break;
			case NORTH:
				x = getBounds().x + (getBounds().width - Bullet.WIDTH) / 2;
				y = getBounds().y - Bullet.HEIGHT;
				break;
			default:
				x = getBounds().x + (getBounds().width - Bullet.WIDTH) / 2;
				y = getBounds().y + getBounds().height;
				break;
		}

		bullets.add(new Bullet(x, y, currentDir));
		bulletTimer = System.currentTimeMillis();
	}

	public static int round(double num, int base) {
		num /= base;
		num = Math.round(num) * base;
		return (int) num;
	}
}
