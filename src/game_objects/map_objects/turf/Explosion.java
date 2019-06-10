package game_objects.map_objects.turf;

import game_objects.Sprite;

public class Explosion extends Sprite {

	private int i = 0;

	public Explosion(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		loadImage("resources/sprites/map/explosion1.png");
		getImageDimensions();
	}

	public void cycle() {
		if(i==10) {
			loadImage("resources/sprites/map/explosion2.png");
		} else if(i==20) {
			loadImage("resources/sprites/map/explosion3.png");
		} else if(i==30) {
			setVisible(false);
		}
		++i;
	}
}
