package game_objects.map_objects;

import game_objects.Sprite;

public abstract class MapObject extends Sprite {

	private boolean collidable;

	public MapObject(int x, int y, boolean collidable) {
		super(x, y);
		this.collidable = collidable;
	}

}
