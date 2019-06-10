package game_objects.map_objects.impassables;

import game_objects.Destructible;
import game_objects.map_objects.MapObject;

public class Base extends MapObject implements Destructible {
	private boolean defeated = false;

	public Base(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/base.png");
		getImageDimensions();
	}

	public boolean isDefeated() {
		return defeated;
	}

	@Override
	public void destroy() {
		loadImage("resources/sprites/map/base_defeated.png");
		defeated = true;
	}


}
