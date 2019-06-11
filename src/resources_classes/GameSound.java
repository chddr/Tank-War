package resources_classes;

import javafx.scene.media.AudioClip;
import java.nio.file.Paths;
import java.util.Random;

public class GameSound{

    public static final String[] battleMusicPath = {"resources/music/battle/jojo.mp3", "resources/music/battle/octopath_traveler.mp3"};
    public static final Random random = new Random();
    private static String lastMusic;

    public static AudioClip getMenuMusicInstance(){
        return new AudioClip(Paths.get("resources/music/menu_music.mp3").toUri().toString());
    }

    public static AudioClip getBattleMusicInstance(){
        String nextMusic = "";
        do {
            nextMusic = battleMusicPath[random.nextInt(battleMusicPath.length)];
        } while (nextMusic.equals(lastMusic));
        lastMusic = nextMusic;
        return new AudioClip(Paths.get(nextMusic).toUri().toString());
    }

    public static AudioClip getStopTimeSoundInstance(){
        return new AudioClip(Paths.get("resources/music/sounds/ZA_WARUDO.mp3").toUri().toString());
    }

    public static AudioClip getExplosionSoundInstance(){
        return new AudioClip(Paths.get("resources/music/sounds/explosion.wav").toUri().toString());
    }

}
