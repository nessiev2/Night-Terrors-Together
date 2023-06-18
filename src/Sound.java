import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    Timer t = new Timer();

    // to store current position
    Long currentFrame;
    Clip backgroundMusic;
    Clip gotCaughtSoundEffect;

    // current status of clip
    String status;

    AudioInputStream audioInputStreamBackgroundMusic, audioInputStreamGotCaught;
    //static String filePath = "res\\KevinMacLeodSneakySnitch.wav";

    // constructor to initialize streams and clip
    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStreamBackgroundMusic = AudioSystem.getAudioInputStream(new File("res\\KevinMacLeodSneakySnitch.wav").getAbsoluteFile());
        audioInputStreamGotCaught = AudioSystem.getAudioInputStream(new File("res\\RobloxOof.wav").getAbsoluteFile());

        // create clip reference
        backgroundMusic = AudioSystem.getClip();
        gotCaughtSoundEffect = AudioSystem.getClip();

        // open audioInputStream to the clip
        backgroundMusic.open(audioInputStreamBackgroundMusic);
        gotCaughtSoundEffect.open(audioInputStreamGotCaught);

        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        //gotCaughtSoundEffect.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playBackgroundMusic() {
        //start the clip
        backgroundMusic.start();
        status = "play";
    }

    public void playGotCaughtSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //t.start();
        gotCaughtSoundEffect.start();

//        while (t.getElapsedTime() <= 1000) {
//            t.stop();
//            if (t.getElapsedTime() <= 1000)
//                restart(gotCaughtSoundEffect);
//        }
        //restart(gotCaughtSoundEffect, audioInputStreamGotCaught, "res\\RobloxOof.wav");
    }

    public void restart(Clip clip) {
        boolean b = true;
        while (b) {
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);

    }


//    public void restart(Clip clip) {
//        currentFrame = 0L;
//        clip.setMicrosecondPosition(0);
//    }

    // Method to restart the audio
//    public void restart(Clip clip, AudioInputStream ais, String file) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
//        clip.stop();
//        clip.close();
//        ais = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
//        clip.open(ais);
//        currentFrame = 0L;
//        clip.setMicrosecondPosition(0);
//    }


}
