package resources_classes;

import javafx.scene.media.AudioClip;
import java.nio.file.Paths;
import java.util.Random;

public class GameSound{

    public static final Random random = new Random();
    /**
     * double music volume from 0 to 1
     */
    public static final double battleMusicVolume = 0.7;
    public static final double menuMusicVolume = 1;
    public static final double explosionSoundVolume = 0.2;
    public static final double boostSoundVolume = 1;
    public static final double stopTimeSoundVolume = 1;

    public static final AudioClip[] stopTimeSound = {getStopTimeSoundInstance(), getStopTimeSoundCountInstance()};

    private static final AudioClip[] battleMusic = {
            new AudioClip(Paths.get("resources/music/battle/jojo_op1_8bit.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/battle/jojo_op2_8bit.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/battle/jojo_op3_8bit.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/battle/octopath_traveler_8bit.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/battle/megalovania_8bit.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/battle/tank!.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/battle/aot.mp3").toUri().toString())
    };
//    public static final String[] battleMusicPath = {
//            "resources/music/battle/jojo_op1_8bit.mp3",
//            "resources/music/battle/jojo_op2_8bit.mp3",
//            "resources/music/battle/jojo_op3_8bit.mp3",
//            "resources/music/battle/octopath_traveler_8bit.mp3",
//            "resources/music/battle/megalovania_8bit.mp3"
//
//    };

//    public static final String[] menuMusicPath = {
//            "resources/music/menu/six_days_war.mp3",
//            "resources/music/menu/paint_it_black.mp3",
//            "resources/music/menu/i'm nuclear.mp3",
//            "resources/music/menu/waste_land.mp3",
//            "resources/music/menu/2+2.mp3"
//    };

    private static final AudioClip[] menuMusic = {
            new AudioClip(Paths.get("resources/music/menu/six_days_war.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/menu/paint_it_black.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/menu/i'm nuclear.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/menu/waste_land.mp3").toUri().toString()),
            new AudioClip(Paths.get("resources/music/menu/2+2.mp3").toUri().toString())
    };

    private static AudioClip lastBattleMusic;
    private static AudioClip lastMenuMusic;

//    public static AudioClip getMenuMusicInstance(){
//        String nextMusic = "";
//        do {
//            nextMusic = menuMusicPath[random.nextInt(menuMusicPath.length)];
//        } while (nextMusic.equals(lastMenuMusic));
//        lastMenuMusic = nextMusic;
//        AudioClip music = new AudioClip(Paths.get(nextMusic).toUri().toString());
//        music.setVolume(menuMusicVolume);
//        return music;
//    }

//    public static AudioClip getBattleMusicInstance(){
//        String nextMusic = "";
//        do {
//            nextMusic = battleMusicPath[random.nextInt(battleMusicPath.length)];
//        } while (nextMusic.equals(lastBattleMusic));
//        lastBattleMusic = nextMusic;
//        AudioClip music = new AudioClip(Paths.get(nextMusic).toUri().toString());
//        music.setVolume(battleMusicVolume);
//        return music;
//    }

    public static AudioClip nextBattleMusic(){
        AudioClip nextMusic;
        do {
            nextMusic = battleMusic[random.nextInt(battleMusic.length)];
        } while (nextMusic == lastBattleMusic);
        lastBattleMusic = nextMusic;
        nextMusic.setVolume(battleMusicVolume);
        return nextMusic;
    }

    public static AudioClip nextMenuMusic(){
        AudioClip nextMusic;
        do {
            nextMusic = menuMusic[random.nextInt(menuMusic.length)];
        } while (nextMusic == lastMenuMusic);
        lastMenuMusic = nextMusic;
        nextMusic.setVolume(menuMusicVolume);
        return nextMusic;
    }


    public static AudioClip getStopTimeSoundInstance(){
        AudioClip audioClip = new AudioClip(Paths.get("resources/music/sounds/ZA_WARUDO.mp3").toUri().toString());
        audioClip.setVolume(stopTimeSoundVolume);
        return audioClip;
    }

    public static AudioClip getStopTimeSoundCountInstance(){
        AudioClip audioClip = new AudioClip(Paths.get("resources/music/sounds/ZA_WARUDO_COUNT.mp3").toUri().toString());
        audioClip.setVolume(stopTimeSoundVolume);
        return audioClip;
    }


    public static AudioClip getExplosionSoundInstance(){
        AudioClip audioClip = new AudioClip(Paths.get("resources/music/sounds/explosion.wav").toUri().toString());
        audioClip.setVolume(explosionSoundVolume);
        return audioClip;
    }
    public static AudioClip getBoostSoundInstance(){
        AudioClip audioClip = new AudioClip(Paths.get("resources/music/sounds/boost.mp3").toUri().toString());
        audioClip.setVolume(boostSoundVolume);
        return audioClip;
    }

    public static AudioClip getWinMusicInstance(){
        AudioClip audioClip = new AudioClip(Paths.get("resources/music/game_end/we_are_the_champions.mp3").toUri().toString());
        audioClip.setVolume(1);
        return audioClip;
    }
    public static AudioClip getDefeatMusicInstance(){
        AudioClip audioClip = new AudioClip(Paths.get("resources/music/game_end/the_real_folk_blues.mp3").toUri().toString());
        audioClip.setVolume(1);
        return audioClip;
    }





}
