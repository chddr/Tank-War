package game_content;

import game_objects.map_objects.MapObject;
import game_objects.movables.Direction;
import game_objects.movables.Tank;
import map_tools.Level;
import map_tools.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	public static final int DELAY = 15;
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
		if(!checkWallCollisions()) {
			tank.move();
		}
		//Synchronizing drawing because of buffering
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Checking collisions of tanks and other objects on the map
	 */
	private boolean checkWallCollisions() {
		for (MapObject mo : map) {
			if (mo.isCollidable() && tank.getTheoreticalBounds().intersects(mo.getBounds()))
				return true;
		}
		return !this.getBounds().contains(tank.getTheoreticalBounds());

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawTank(g);
		drawMapObjects(g);
	}

	/**
	 * Draw a tank on the specified graphics
	 *
	 * @param g Graphics to draw on
	 */
	private void drawTank(Graphics g) {
		g.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
	}

	/**
	 * Draw all objects that are on the map
	 *
	 * @param g Graphics that we draw on
	 */
	private void drawMapObjects(Graphics g) {
		for (MapObject mo : map)
			if (mo.isVisible())
				g.drawImage(mo.getImage(), mo.getX(), mo.getY(), this);

	}

	/**
	 * Method for running the game in a thread for continuous and uninterrupted game performance. We use a while-loop to perform some actions specified in a cycle method and then repaint the whole game.
	 */
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
