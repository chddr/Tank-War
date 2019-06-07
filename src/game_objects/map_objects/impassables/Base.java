package game_objects.map_objects.impassables;

import game_objects.Destructible;

public class Base extends Impassable implements Destructible {
	public Base(int x, int y) {
		super(x, y);

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
