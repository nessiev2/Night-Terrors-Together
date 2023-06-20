import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Player {
    Sound sound;
    private BufferedImage img;
    private Image gif = null;
    protected boolean right = false, left = false, up = false, down = false, interact = false, standing = true;
    private int speed = 10;
    private int x, centerX;
    private int y, centerY;
    private int width = 80;
    private int height = 120;
    private boolean isSpillingWater;
    private boolean isHolding;
    public boolean getIsHolding() {return isHolding; }
    public void changeIsHolding(boolean b) { isHolding = b; }
    private boolean PCaughtSound = true;
    private int playerN;
    private boolean isFacingLeft = false;
    private boolean isCaught = false;
    protected int croom = 5;

    public void changeIsSpillingWater(boolean b) { isSpillingWater = b; }
    public boolean getPCaughtSound() { return PCaughtSound; }
    public void changePCaughtSound(boolean b) { PCaughtSound = b; }
    public void updateCRoom(int i) { croom = i; }
    public void changeFaceDirection(boolean b) { isFacingLeft = b; }
    public int getX() {
        return x;
    }
    public int getY() { return y; }
    public int getCenterX() {
        return centerX;
    }
    public int getCenterY() { return centerY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean getIsCaught() { return isCaught; }
    public boolean getIsSpillingWater() {
        return isSpillingWater;
    }

    public void changeIsCaught(boolean c) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        isCaught = c;
        if (!c) {
            sound.playJailBreakSoundEffect();
        }
    }

    public void changeSpeed(int x) {speed = x; }
    public int getPlayerN(){
        return playerN;
    }
    public void initializePlayer() {
        PCaughtSound = true;
        isCaught = false;
        isSpillingWater = false;
        if (playerN == 1){
            spawnPlayer(NTT.SCREEN_WIDTH/2-width, 660);
        } else {
            spawnPlayer(NTT.SCREEN_WIDTH/2-2*width, 660);
        }
    }

    public void regSound(Sound sound) {
        this.sound = sound;
    }

    public Player(int playerN) {
        this.playerN = playerN;
        if (playerN == 1) {
            try {
                img = ImageIO.read(new File("res\\P1WalkR.png"));
                gif = Toolkit.getDefaultToolkit().createImage("res\\P1WalkGif.gif");
            } catch (IOException e) { System.out.println("p1 no image"); }
        } else { // player == 2
            try {
                img = ImageIO.read(new File("res\\P2WalkR.png"));
                gif = Toolkit.getDefaultToolkit().createImage("res\\P2WalkGif.gif");
            } catch (IOException e) { System.out.println("p2 no image"); }
        }
    }


    public void paint(Graphics2D g2d, DoMess mess) {
        if (standing){
            if (isFacingLeft){
                g2d.drawImage(img, getX()+width, getY(), -width, height, null);
            }
            else {
                g2d.drawImage(img, getX(), getY(), width, height, null);
            }
        } else {
            if (left || isFacingLeft) {
                //g2d.drawImage(img, getX(), getY(), width, height, null);
                g2d.drawImage(gif, getX()+width, getY(), -width, height, null);
            } else {
                //g2d.drawImage(img, getX() + getWidth(), getY(), -width, height, null);
                g2d.drawImage(gif, getX(), getY(), width, height, null);
            }
        }
    }

    public void spawnPlayer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e, DoHack doHack8, int currentClassroom, DoDissection dissection4) {}
    public void keyReleased(Player p2, int currentClassroom, KeyEvent e, DoArson[] arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess3, DoHack doHack8, DoVendingMachine[] doVendingMachine, DoBadReaction badReaction, DoStealTests burnTests7, DoDissection dissection4) throws UnsupportedAudioFileException, LineUnavailableException, IOException {}

    public boolean checkPlayerCaught(Teacher[] t) {
        if (!checkRight(t) || !checkLeft(t) || !checkDown(t) || !checkUp(t)) {
            isCaught = true;
        }
        return isCaught;
    }
    public boolean checkTeacher(Teacher[] t) {
        if (!checkRight(t) || !checkLeft(t) || !checkDown(t) || !checkUp(t)) {
            return true;
        }
        return false;
    }

    public void move(Thing[] t) {

        if (!left && !right && !up && !down){
            standing = true;
        } else {
            standing = false;
        }
        if (!isCaught) {
            if (right && checkRight(t) && (x + width + speed < NTT.SCREEN_WIDTH)) {
                x += speed;
            }
            if (left && checkLeft(t) && (x - speed > 0)) {
                x -= speed;
            }
            if (down && checkDown(t) && y + height + speed < NTT.SCREEN_HEIGHT) {
                y += speed;
            }
            if (up && checkUp(t) && (y > 155)) {
                y -= speed;
            }
        }

        centerX = (2*x + width)/2;
        centerY = (2*y + height)/2;
    }

    // returns false if caught by teacher
    // returns true if not touching
    public boolean checkRight(Thing[] things) {
        for (Thing t:things)
            if (x < t.getX() && x + width + speed > t.getX() && y + height > t.getY() && y < t.getY() + t.getHeight()) {
                return false;
            }
        return true;
    }

    public boolean checkLeft(Thing[] things) {
        for (Thing t:things)
            if (x > t.getX() && x - speed < t.getX() + t.getWidth() && y + height > t.getY() && y < t.getY() + t.getHeight()) {
                return false;
            }
        return true;
    }

    public boolean checkDown(Thing[] things) {
        for (Thing t:things)
            if (y < t.getY() && y + height + speed > t.getY() && x + width > t.getX() && x < t.getX() + t.getWidth()) {
                return false;
            }
        return true;
    }

    public boolean checkUp(Thing[] things) {
        for (Thing t:things)
            if (y > t.getY() && y - speed < t.getY() + t.getHeight() && x + width > t.getX() && x < t.getX() + t.getWidth()) {
                return false;
            }
        return true;
    }


}