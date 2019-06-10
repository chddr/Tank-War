package resources_classes;

import javafx.scene.media.AudioClip;
import java.nio.file.Paths;

public class GameSound{

    public static AudioClip getMenuMusicInstance(){
        return new AudioClip(Paths.get("resources/music/menu_music.mp3").toUri().toString());
    }

    public static AudioClip getStopTimeSoundInstance(){
        return new AudioClip(Paths.get("resources/music/ZA_WARUDO.mp3").toUri().toString());
    }

    public static AudioClip getBattleMusicInstance(){
        return new AudioClip(Paths.get("resources/music/battle_music.mp3").toUri().toString());
    }

}
