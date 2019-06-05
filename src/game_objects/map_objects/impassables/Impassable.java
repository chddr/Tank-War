package game_objects.map_objects.impassables;

import game_objects.map_objects.MapObject;

public class Impassable extends MapObject {

	public Impassable(int x, int y, boolean destructible) {
		super(x, y, true, destructible);
	}
}
