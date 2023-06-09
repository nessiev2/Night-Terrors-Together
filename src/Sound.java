import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    // to store current position
    private Clip backgroundMusic, gotCaughtSoundEffect, taskCompleteSoundEffect, teacherJumpscareSoundEffect, jailBreakSoundEffect, gameOverSoundEffect;

    // current status of clip
    private String status;

    private AudioInputStream audioInputStreamBackgroundMusic, audioInputStreamGotCaught, audioInputStreamTaskComplete, audioInputStreamTeacherJumpscare, audioInputStreamJailBreak, audioInputStreamGameOver;
    //static String filePath = "res\\KevinMacLeodSneakySnitch.wav";

    // constructor to initialize streams and clip
    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // create AudioInputStream object
        audioInputStreamBackgroundMusic = AudioSystem.getAudioInputStream(new File("res\\KevinMacLeodSneakySnitch.wav").getAbsoluteFile());
        audioInputStreamGotCaught = AudioSystem.getAudioInputStream(new File("res\\RobloxOof.wav").getAbsoluteFile());
        audioInputStreamTaskComplete = AudioSystem.getAudioInputStream(new File("res\\SparklesSoundEffect.wav").getAbsoluteFile());
        audioInputStreamTeacherJumpscare = AudioSystem.getAudioInputStream(new File("res\\JoyiIreneScreamDanganronpa.wav").getAbsoluteFile());
        audioInputStreamJailBreak = AudioSystem.getAudioInputStream(new File("res\\Happy.wav").getAbsoluteFile());
        audioInputStreamGameOver = AudioSystem.getAudioInputStream(new File("res\\DanganronpaPollutionNoise.wav").getAbsoluteFile());

        // create clip reference
        backgroundMusic = AudioSystem.getClip();
        gotCaughtSoundEffect = AudioSystem.getClip();
        taskCompleteSoundEffect = AudioSystem.getClip();
        teacherJumpscareSoundEffect = AudioSystem.getClip();
        jailBreakSoundEffect = AudioSystem.getClip();
        gameOverSoundEffect = AudioSystem.getClip();

        // open audioInputStream to the clip
        backgroundMusic.open(audioInputStreamBackgroundMusic);
        gotCaughtSoundEffect.open(audioInputStreamGotCaught);
        taskCompleteSoundEffect.open(audioInputStreamTaskComplete);
        teacherJumpscareSoundEffect.open(audioInputStreamTeacherJumpscare);
        jailBreakSoundEffect.open(audioInputStreamJailBreak);
        gameOverSoundEffect.open(audioInputStreamGameOver);

        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        //gotCaughtSoundEffect.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void soundMaintenance() {
        if (!gotCaughtSoundEffect.isActive()) {
            gotCaughtSoundEffect.setMicrosecondPosition(0);
        }
        if (!taskCompleteSoundEffect.isActive()) {
            taskCompleteSoundEffect.setMicrosecondPosition(0);
        }
        if (!teacherJumpscareSoundEffect.isActive()) {
            teacherJumpscareSoundEffect.setMicrosecondPosition(0);
        }
        if (!jailBreakSoundEffect.isActive()) {
            jailBreakSoundEffect.setMicrosecondPosition(0);
        }
        if (!gameOverSoundEffect.isActive()) {
            gameOverSoundEffect.setMicrosecondPosition(0);
        }
    }

    public void stopSound() {
        gotCaughtSoundEffect.stop();
        taskCompleteSoundEffect.stop();
        teacherJumpscareSoundEffect.stop();
        jailBreakSoundEffect.stop();
        gameOverSoundEffect.stop();

        soundMaintenance();
    }

    public void playBackgroundMusic() {
        backgroundMusic.start();
    }

    public void playGotCaughtSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        gotCaughtSoundEffect.start();
    }

    public void playTaskCompleteSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        taskCompleteSoundEffect.start();
    }

    public void playTeacherJumpscareSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        teacherJumpscareSoundEffect.start();
    }

    public void playJailBreakSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        jailBreakSoundEffect.start();
    }

    public void playGameOverSoundEffect() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        gameOverSoundEffect.start();
    }
}
