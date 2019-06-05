package game_objects.map_objects.impassables;

public class BrickWall extends Impassable {

	public BrickWall(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources//sprites//brick_wall.png");
		getImageDimensions();
	}
}
