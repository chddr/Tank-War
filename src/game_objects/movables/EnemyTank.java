package game_objects.movables;

public class EnemyTank extends Tank {

	public EnemyTank(int x, int y, Direction dir) {
		super(x, y, dir, 4000);
		init();
	}

	private void init() {
		loadImage("resources/sprites/enemy_tank/tank_%s.png");
		getImageDimensions();
	}

	@Override
	public void move() {
//		changeDirection(Direction.values()[new Random().nextInt(Direction.values().length)]);
		super.move();
		fire();
	}
}
