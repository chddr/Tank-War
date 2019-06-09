package game_objects.movables;

import game_content.GameField;
import game_objects.Destructible;
import game_objects.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tank extends Movable implements Destructible {

	/**
	 * Directions images in such an order: west, east, north, south
	 */
	private final static int SPEED = 2;

	public Tank(int x, int y, Direction dir) {
		super(x, y, dir);

		init();
	}

	private void init() {
		loadImage("resources/sprites/player_tank/tank_%s.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}

	public void changeDirection(Direction dir) {
		dx = 0;
		dy = 0;
		//Slight move to fit the tank in the narrow corridors and other places
		if (Direction.isTurn(currentDir, dir)) {
			double coord;
			switch (currentDir) {
				case WEST:
				case EAST:
					coord = getX();
					coord /= GameField.BYTE;
					coord = Math.round(coord) * GameField.BYTE;
					setX((int) coord);
					break;
				case NORTH:
				case SOUTH:
					coord = getY();
					coord /= GameField.BYTE;
					coord = Math.round(coord) * GameField.BYTE;
					setY((int) coord);
					break;
			}
		}
		currentDir = dir;

		switch (dir) {
			case WEST:
				image = directions[0];
				dx = -SPEED;
				break;
			case EAST:
				image = directions[1];
				dx = SPEED;
				break;
			case NORTH:
				image = directions[2];
				dy = -SPEED;
				break;
			case SOUTH:
				image = directions[3];
				dy = SPEED;
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}


}
