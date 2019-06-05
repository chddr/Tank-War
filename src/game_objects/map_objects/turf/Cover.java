package game_objects.map_objects.turf;

public class Cover extends Turf {
	public Cover(int x, int y) {
		super(x, y, false);

		init();
	}

	private void init() {
		loadImage("resources/sprites/cover.png");
		getImageDimensions();
	}
}
