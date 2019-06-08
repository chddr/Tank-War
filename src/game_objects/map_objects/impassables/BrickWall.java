package game_objects.map_objects.impassables;

import game_objects.Destructible;
import game_objects.map_objects.MapObject;

public class BrickWall extends MapObject implements Destructible {

	public BrickWall(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/brick_wall.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}
}
