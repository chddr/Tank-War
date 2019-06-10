package game_content;

import game_objects.Destructible;
import game_objects.map_objects.MapObject;
import game_objects.map_objects.turf.Explosion;
import game_objects.movables.Bullet;
import game_objects.movables.Direction;
import game_objects.movables.Tank;
import map_tools.Level;
import map_tools.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class GameField extends JPanel implements Runnable {

	/**
	 * Scale (for resize)
	 */
	public static final int SCALE = 2;
	/**
	 * Size of the game map relative to the tile size. Actually its twice as small relative to the Tank because every map tile is divided into four destructible parts
	 */
	public static final int MAP_SIZE = 26;
	/**
	 * HOLY CONSTANT - Size of the one tile
	 */
	public static final int BYTE = 8 * SCALE;
	/**
	 * Dimensions of a game field (height and width are always equal)
	 */
	public static final int FIELD_DIMENSIONS = MAP_SIZE * BYTE;
	/**
	 * Delay in miliseconds
	 */
	public static final int DELAY = 20;
	/**
	 * How many frames it takes for tenth second to pass
	 */
	public static final int TENTH_OF_SECOND = 100 / DELAY;
	private List<Explosion> explosions = new LinkedList<>();
	private Map map;
	private Tank tank;

	private Thread animator;


	public GameField() {
		initGameField();
	}

	/**
	 * Initialization method
	 */
	private void initGameField() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(FIELD_DIMENSIONS, FIELD_DIMENSIONS));
		setFocusable(true);

		initMap();

	}

	private void initMap() {
		addKeyListener(new Adapter());
		map = Map.getLevelMap(Level.TWO);
		tank = new Tank(8 * BYTE, 24 * BYTE, Direction.NORTH);
	}

	/**
	 * Overriden method. Used to start the Thread when it's added to the JFrame
	 */
	@Override
	public void addNotify() {
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	/**
	 * All actions that should be performed every game tick
	 */
	private void cycle() {
		if (!checkWallCollisions()) {
			tank.move();
		}
		updateBullets();
		updateMap();
		//Synchronizing drawing because of buffering
		Toolkit.getDefaultToolkit().sync();
	}

	private void updateMap() {
		map.removeIf(mapObject -> !mapObject.isVisible());
	}

	private void updateBullets() {
		List<Bullet> bullets = tank.getBullets();
		bullets.removeIf(bullet -> !bullet.isVisible());

		for (Bullet b : bullets) {
			Rectangle bBounds = b.getTheoreticalBounds();
			for (MapObject mo : map) {
				if (mo instanceof Destructible && bBounds.intersects(mo.getBounds())) {
					((Destructible) mo).destroy();
					b.destroy();
					explosions.add(b.getExplosion());
				}

			}
			if (!this.getBounds().contains(bBounds))
				b.destroy();
			b.move();
		}

	}

	/**
	 * Checking collisions of tanks and other objects on the map
	 */
	private boolean checkWallCollisions() {
		Rectangle tBounds = tank.getTheoreticalBounds();
		for (MapObject mo : map) {
			if (mo.isCollidable() && tBounds.intersects(mo.getBounds()))
				return true;
		}
		return !this.getBounds().contains(tBounds);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawTank(g);
		drawMapObjects(g);
		drawBullets(g);
		drawExplosion(g);
	}

	/**
	 * Draw bullets on graphics
	 *
	 * @param g Graphics we draw on
	 */
	private void drawBullets(Graphics g) {
		List<Bullet> bullets = tank.getBullets();
		for (Bullet b : bullets) {
			if (b.isVisible()) {
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}

	/**
	 * Draw player tank on graphics
	 *
	 * @param g Graphics we draw on
	 */
	private void drawTank(Graphics g) {
		g.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
	}

	/**
	 * Draw all objects that are on the map on graphics
	 *
	 * @param g Graphics we draw on
	 */
	private void drawMapObjects(Graphics g) {
		for (MapObject mo : map)
			if (mo.isVisible())
				g.drawImage(mo.getImage(), mo.getX(), mo.getY(), this);

	}

	/**
	 * Draw explosions on graphics
	 *
	 * @param g Graphics we draw on
	 */
	private void drawExplosion(Graphics g) {
		for (Explosion ex : explosions)
			if (ex.isVisible()) {
				g.drawImage(ex.getImage(), ex.getX(), ex.getY(), this);
				ex.cycle();
			}
		explosions.removeIf(explosion -> !explosion.isVisible());

	}

	/**
	 * Method for running the game in a thread for continuous and uninterrupted game performance. We use a while-loop to perform some actions specified in a cycle method and then repaint the whole game.
	 */
	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

			cycle();
			repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {

				String msg = String.format("Thread interrupted: %s", e.getMessage());

				JOptionPane.showMessageDialog(this, msg, "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			beforeTime = System.currentTimeMillis();
		}
	}

	private class Adapter extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			tank.fire();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
				case KeyEvent.VK_LEFT:
					tank.changeDirection(Direction.WEST);
					break;
				case KeyEvent.VK_RIGHT:
					tank.changeDirection(Direction.EAST);
					break;
				case KeyEvent.VK_UP:
					tank.changeDirection(Direction.NORTH);
					break;
				case KeyEvent.VK_DOWN:
					tank.changeDirection(Direction.SOUTH);
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			tank.keyReleased(e);
		}
	}
}
