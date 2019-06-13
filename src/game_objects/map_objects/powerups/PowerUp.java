package game_objects.map_objects.powerups;

import game_objects.Sprite;

import java.util.Random;

public class PowerUp extends Sprite {

	private Type type;

	public PowerUp(int x, int y) {
		super(x, y);

		init();
	}

	private void init() {
		type = Type.values()[new Random().nextInt(Type.values().length)];
		switch (type) {
			case UPGRADE:
				loadImage("resources/sprites/powerups/upgrade.png");
				break;
			case HEALTH:
				loadImage("resources/sprites/powerups/health.png");
				break;
			case TIME_STOP:
				loadImage("resources/sprites/powerups/time.png");
				break;
		}
		getImageDimensions();
	}

	public Type getType() {
		return type;
	}

	public enum Type {
		UPGRADE, HEALTH, TIME_STOP
	}
}
