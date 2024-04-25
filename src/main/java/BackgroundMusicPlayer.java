import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/*
 * This is BackgroundMusicPlayer class
 * Music Player in charge of playing background music
 * and this class utilized Singleton Pattern to prevent the music from overlapping
 *
 *  */
public class BackgroundMusicPlayer {
    private static BackgroundMusicPlayer instance;
    private Clip clip;
    private BackgroundMusicPlayer() {}
    public static BackgroundMusicPlayer getInstance() {
        if (instance == null) {
            instance = new BackgroundMusicPlayer();
        }
        return instance;
    }
    public void play(String location) {
        try {
            File musicPath = new File(location);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
