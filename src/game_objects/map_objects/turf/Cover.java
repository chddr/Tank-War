package game_objects.map_objects.turf;

import game_objects.map_objects.MapObject;

public class Cover extends MapObject {
	public Cover(int x, int y) {
		super(x, y, false);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/cover.png");
		getImageDimensions();
	}
}
