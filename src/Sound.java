import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    // to store current position
    private Clip backgroundMusic;
    private Clip gotCaughtSoundEffect;
    private Clip taskCompleteSoundEffect;

    // current status of clip
    private String status;

    private AudioInputStream audioInputStreamBackgroundMusic, audioInputStreamGotCaught, audioInputStreamTaskComplete;
    //static String filePath = "res\\KevinMacLeodSneakySnitch.wav";

    // constructor to initialize streams and clip
    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStreamBackgroundMusic = AudioSystem.getAudioInputStream(new File("res\\KevinMacLeodSneakySnitch.wav").getAbsoluteFile());
        audioInputStreamGotCaught = AudioSystem.getAudioInputStream(new File("res\\RobloxOof.wav").getAbsoluteFile());
        audioInputStreamTaskComplete = AudioSystem.getAudioInputStream(new File("res\\SparklesSoundEffect.wav").getAbsoluteFile());

        // create clip reference
        backgroundMusic = AudioSystem.getClip();
        gotCaughtSoundEffect = AudioSystem.getClip();
        taskCompleteSoundEffect = AudioSystem.getClip();

        // open audioInputStream to the clip
        backgroundMusic.open(audioInputStreamBackgroundMusic);
        gotCaughtSoundEffect.open(audioInputStreamGotCaught);
        taskCompleteSoundEffect.open(audioInputStreamTaskComplete);

        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        //gotCaughtSoundEffect.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playBackgroundMusic() {
        //start the clip
        backgroundMusic.start();
        status = "play";
    }

    public void soundMaintenance() {
        if (!gotCaughtSoundEffect.isActive()) {
            gotCaughtSoundEffect.setMicrosecondPosition(0);
        }
        if (!taskCompleteSoundEffect.isActive()) {
            taskCompleteSoundEffect.setMicrosecondPosition(0);
        }
    }

    public void playGotCaughtSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        gotCaughtSoundEffect.start();
    }

    public void playTaskCompleteSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        taskCompleteSoundEffect.start();
    }
}
