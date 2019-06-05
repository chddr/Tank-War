package game_objects.map_objects;

import game_objects.Sprite;

public class MapObject extends Sprite {

	private boolean collidable;
	private boolean destructible;

	public MapObject(int x, int y, boolean collidable, boolean destructible) {
		super(x, y);
		this.collidable = collidable;
		this.destructible = destructible;
	}

}
gi