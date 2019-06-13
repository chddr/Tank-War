package game_objects.map_objects.turf;

import game_content.GameField;
import game_objects.Sprite;
import javafx.scene.media.AudioClip;
import resources_classes.GameSound;

public class Explosion extends Sprite {

	private final static int DELAY = GameField.TENTH_OF_SECOND;

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
		AudioClip explosionSound = GameSound.getExplosionSoundInstance();
		explosionSound.setVolume(GameSound.explosionSoundVolume);
		if(i == DELAY ) {
			loadImage("resources/sprites/map/explosion2.png");
			explosionSound.play();
		} else if(i== DELAY *2) {
			loadImage("resources/sprites/map/explosion3.png");
		} else if(i== DELAY *3) {
			setVisible(false);
		} else if(i== DELAY *4)
			explosionSound.stop();
		++i;
	}
}
