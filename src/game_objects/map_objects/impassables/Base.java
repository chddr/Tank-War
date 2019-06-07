package game_objects.map_objects.impassables;

public class Base extends Impassable implements Destructible {
	public Base(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		loadImage("resources/sprites/base.png");
		getImageDimensions();
	}

	@Override
	public void destroy() {
		loadImage("resources/sprites/base_defeated.png");
		getImageDimensions();
	}
}
