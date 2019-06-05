package game_objects.map_objects;

public class SteelWall extends Wall {

	public SteelWall(int x, int y) {
		super(x,y, false);

		init();
	}

	private void init() {
		loadImage("resources/sprites/brick_wall.png");
		getImageDimensions();
	}
}
