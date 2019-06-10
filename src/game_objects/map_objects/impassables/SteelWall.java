package game_objects.map_objects.impassables;

import game_objects.Destructible;
import game_objects.map_objects.MapObject;

public class SteelWall extends MapObject implements Destructible {

	public SteelWall(int x, int y) {
		super(x,y, true);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/steel_wall.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}
}
