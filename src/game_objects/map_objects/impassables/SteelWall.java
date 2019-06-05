package game_objects.map_objects.impassables;

public class SteelWall extends Impassable {

	public SteelWall(int x, int y) {
		super(x,y);

		init();
	}

	private void init() {
		loadImage("resources/sprites/steel_wall.png");
		getImageDimensions();
	}
}
