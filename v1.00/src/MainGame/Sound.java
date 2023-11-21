package MainGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    public static Clip clipMusic;

    public static void missileSound() {
        try {
            String filePath = "./src/resources/bullet.wav";
            File soundPath = new File(filePath);

            if (soundPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("Cant find sound file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void destroySound() {
        try {
            String filePath = "./src/resources/destroy.wav";
            File soundPath = new File(filePath);

            if (soundPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } else {
                System.out.println("Cant find sound file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void playMusic() {
        try {
            String filePath = "./src/resources/music.wav";
            File soundPath = new File(filePath);

            if (soundPath.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPath);
                clipMusic = AudioSystem.getClip();
                clipMusic.open(audioInputStream);
                clipMusic.start();
            } else {
                System.out.println("Cant find music file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
