package game_content;

import map_tools.Level;
import map_tools.Map;
import game_objects.map_objects.MapObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameField extends JPanel implements Runnable, ActionListener {

	/**
	 * Size of the game map relative to the tile size. Actually its twice as small relative to the tank because every map tile is divided into four destructible parts
	 */
	public static final int MAP_SIZE = 26;
	/**
	 * HOLY CONSTANT - BYTE
	 */
	public static final int BYTE = 8;
	/**
	 * Dimensions of a game field (height and width are always equal)
	 */
	public static final int FIELD_DIMENSIONS = MAP_SIZE * BYTE;
	/**
	 * Delay in miliseconds
	 */
	public static final int DELAY = 25;
	private Map map = Map.getLevelMap(Level.ONE);

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
		//TODO

		//Synchronizing drawing because of buffering
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawMapObjects(g);
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
