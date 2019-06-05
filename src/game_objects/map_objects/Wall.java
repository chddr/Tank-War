package game_objects.map_objects;

import game_objects.Sprite;

public class Wall extends Sprite {

	private boolean destructible;

	public Wall(int x, int y, boolean destructible) {
		super(x,y);

		this.destructible = destructible;
	}
}
