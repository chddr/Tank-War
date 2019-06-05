package game_objects.map_objects.impassables;

public class BrickWall extends Impassable implements Destructible {

	public BrickWall(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		loadImage("resources//sprites//brick_wall.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {

	}
}
