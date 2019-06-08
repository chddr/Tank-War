package game_content;

import game_objects.map_objects.MapObject;
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
	public static final int SCALE = 4;
	/**
	 * Size of the game map relative to the tile size. Actually its twice as small relative to the Tank because every map tile is divided into four destructible parts
	 */
	public static final int MAP_SIZE = 26;
	/**
	 * HOLY CONSTANT - BYTE
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
		map = Map.getLevelMap(Level.ONE);
		tank = new Tank(8 * BYTE, 24 * BYTE);
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

	private void cycle() {
		tank.move();
		checkCollisions();
		//Synchronizing drawing because of buffering
		Toolkit.getDefaultToolkit().sync();
	}

	private void checkCollisions() {
		for (MapObject mo : map) {
			if (mo.isCollidable()) {
				if (mo.getBounds().intersects(tank.getBounds())) {
					tank.setVisible(false);
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawTank(g);
		drawMapObjects(g);
	}

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
			tank.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			tank.keyReleased(e);
		}
	}
}
