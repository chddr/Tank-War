package game_objects.movables;

import game_objects.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Movable extends Sprite {

	protected BufferedImage[] directions;
	protected int dx, dy;
	protected Direction currentDir;

	public Movable(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {

	}


	/**
	 * Method that moves the object on the board. Should be used only when check for collision was already made.
	 */
	public void move() {
		setX(dx + getX());
		setY(dy + getY());
	}

	/**
	 * Method we use to see if object can move. If it theoretically moved and collided with something, we don't move;
	 *
	 * @return Returns bounds that tank <u><b>would have</b></u> if it moved at this moment.
	 */
	public Rectangle getTheoreticalBounds() {
		Rectangle rect = getBounds();
		rect.x += dx;
		rect.y += dy;
		return rect;
	}

}
