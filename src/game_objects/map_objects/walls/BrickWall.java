package game_objects.map_objects.walls;

public class BrickWall extends Wall {

	public BrickWall(int x, int y) {
		super(x, y, true);

		init();
	}

	private void init() {
		loadImage("resources//sprites//brick_wall.png");
		getImageDimensions();
	}
}
