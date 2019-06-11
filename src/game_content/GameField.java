package game_content;

import game_objects.Destructible;
import game_objects.map_objects.MapObject;
import game_objects.map_objects.impassables.Base;
import game_objects.map_objects.turf.Explosion;
import game_objects.movables.*;
import javafx.scene.media.AudioClip;
import map_tools.Level;
import map_tools.Map;
import resources_classes.GameSound;

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
	public static final int SCALE = 3;
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
	private List<Explosion> explosions;
	private List<Tank> tanks;
	private List<Bullet> bullets;
	private Base base;
	private Map map;
	private PlayerTank playerTank;
	
	private Thread animator;
	private GameFieldPanel gameFieldPanel;

	public GameField(Level level, GameFieldPanel gameFieldPanel) {
		this.gameFieldPanel = gameFieldPanel;
		initGameField(level);
	}

	/**
	 * Initialization method
	 */
	private void initGameField(Level level) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(FIELD_DIMENSIONS, FIELD_DIMENSIONS));
		setFocusable(true);
		initMap(level);

	}

	private void initMap(Level level) {
		addKeyListener(new Adapter());
		map = Map.getLevelMap(level);
		base = map.getBase();
		playerTank = new PlayerTank(8 * BYTE, 24 * BYTE, Direction.NORTH);
		explosions = new LinkedList<>();
		tanks = new LinkedList<>();
		bullets = new LinkedList<>();
		tanks.add(playerTank);
		for (int i = 0; i < 3; i++) {
			tanks.add(new EnemyTank(i * BYTE * 12, 0, Direction.SOUTH));
		}
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
		checkWinCondtions();
		checkAllTanksCollision();
		updateBullets();
		//Synchronizing drawing because of buffering
		Toolkit.getDefaultToolkit().sync();
	}

	private void checkAllTanksCollision() {
		for (Tank t : tanks) {
			if (!checkWallCollisions(t) && !checkTankCollisions(t))
				t.move();
		}
	}

	/**
	 * Checking collisions of tanks with other tanks on the map
	 */
	private boolean checkTankCollisions(Tank tank) {
		Rectangle tBounds = tank.getTheoreticalBounds();
		for (Tank t: tanks ) {
			if(t!=tank && tBounds.intersects(t.getBounds()))
				return true;
		}
		return false;
	}

	/**
	 * Checking collisions of tanks with other objects on the map
	 */
	private boolean checkWallCollisions(Tank tank) {
		Rectangle tBounds = tank.getTheoreticalBounds();
		for (MapObject mo : map) {
			if (mo.isCollidable() && tBounds.intersects(mo.getBounds()))
				return true;
		}
		return !this.getBounds().contains(tBounds);

	}

	private void checkWinCondtions() {
		if (base.isDefeated()) {
			gameFieldPanel.gameLost();
			Thread.currentThread().stop();
		}
	}

	private void updateBullets() {
		bullets = new LinkedList<>();
		for(Tank t : tanks) {
			t.getBullets().removeIf(bullet -> !bullet.isVisible());
			bullets.addAll(t.getBullets());
		}
		for (Bullet b : bullets) {
			Rectangle bBounds = b.getTheoreticalBounds();
			for (Bullet b1 : bullets) {
				if(b!=b1 && bBounds.intersects(b1.getBounds())) {
					b.destroy();
					b1.destroy();
					explosions.add(b.getExplosion());
					explosions.add(b1.getExplosion());
				}
			}
			for (MapObject mo : map) {
				if (mo instanceof Destructible && bBounds.intersects(mo.getBounds())) {
					((Destructible) mo).destroy();
					b.destroy();
					explosions.add(b.getExplosion());
				}

			}
			for (Tank t : tanks) {
				if (bBounds.intersects(t.getBounds())) {
					t.destroy();
					b.destroy();
					explosions.add(b.getExplosion());
				}
			}
			if (!this.getBounds().contains(bBounds))
				b.destroy();
			b.move();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawTanks(g);
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
	private void drawTanks(Graphics g) {
		tanks.removeIf(enemyTank -> !enemyTank.isVisible());
//		g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), this);
		for (Tank tank : tanks) {
			g.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
		}
	}

	/**
	 * Draw all objects that are on the map on graphics
	 *
	 * @param g Graphics we draw on
	 */
	private void drawMapObjects(Graphics g) {
		map.removeIf(mapObject -> !mapObject.isVisible());
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
		explosions.removeIf(explosion -> !explosion.isVisible());
		for (Explosion ex : explosions) {
			g.drawImage(ex.getImage(), ex.getX(), ex.getY(), this);
			ex.cycle();
		}
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
			playerTank.fire();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
				case KeyEvent.VK_LEFT:
					playerTank.changeDirection(Direction.WEST);
					break;
				case KeyEvent.VK_RIGHT:
					playerTank.changeDirection(Direction.EAST);
					break;
				case KeyEvent.VK_UP:
					playerTank.changeDirection(Direction.NORTH);
					break;
				case KeyEvent.VK_DOWN:
					playerTank.changeDirection(Direction.SOUTH);
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			playerTank.keyReleased(e);
		}
	}
}
