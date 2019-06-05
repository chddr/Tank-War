package game_objects.map_objects.turf;

public class Water extends Turf {
	public Water(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources/sprites/water.gif");
		getImageDimensions();
	}
}
