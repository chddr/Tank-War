package game_objects.map_objects.impassables;

import game_objects.Destructible;
import game_objects.map_objects.MapObject;

public class Base extends MapObject implements Destructible {
	public Base(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/base.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {
	}
}
