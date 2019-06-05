package game_objects.map_objects.turf;

import game_objects.map_objects.MapObject;

public abstract class Turf extends MapObject {
	public Turf(int x, int y, boolean collidable) {
		super(x, y, collidable, false);
	}
}
