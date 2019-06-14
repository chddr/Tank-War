package game_objects.movables;

import java.util.Random;

public class EnemyTank extends Tank {

	public EnemyTank(int x, int y, Direction dir) {
		super(x, y, dir, 4000);
		init();
	}

	private void init() {
		loadImage("resources/sprites/enemy_tank/tank_%s.png");
		changeDirection(Direction.SOUTH);
		getImageDimensions();
	}

	@Override
	public void move() {
		super.move();
		fire();
	}
}
