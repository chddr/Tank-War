package game_objects.map_objects.impassables;

import game_objects.map_objects.MapObject;

public abstract class Impassable extends MapObject {

	public Impassable(int x, int y) {
		super(x, y, true);
	}
}
