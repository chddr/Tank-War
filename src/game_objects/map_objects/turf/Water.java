package game_objects.map_objects.turf;

import game_objects.map_objects.MapObject;

public class Water extends MapObject {
	public Water(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/water.gif");
		getImageDimensions();
	}
}
