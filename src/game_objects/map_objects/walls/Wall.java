package game_objects.map_objects.walls;

import game_objects.map_objects.MapObject;

public class Wall extends MapObject {

	public Wall(int x, int y, boolean destructible) {
		super(x, y, true, destructible);
	}
}
