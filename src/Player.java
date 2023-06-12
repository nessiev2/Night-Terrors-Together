import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Player {
    private BufferedImage img = null;
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
    public void changeIsSpillingWater(boolean b) { isSpillingWater = b; }

    private int playerN;
    private boolean isFacingLeft = false;

    public void changeFaceDirection(boolean b) { isFacingLeft = b; }
    private boolean isCaught = false;

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

    public void changeIsCaught(boolean c) {
        isCaught = c;
    }
    public void changeSpeed(int x) {speed = x; }
    public int getPlayerN(){
        return playerN;
    }
    public void initializePlayer() {
        isCaught = false;
        isSpillingWater = false;
        if (playerN == 1){
            spawnPlayer(Main.SCREEN_WIDTH/2-width, 660);
        } else {
            spawnPlayer(Main.SCREEN_WIDTH/2-2*width, 660);
        }
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
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(Player p2, int currentClassroom, KeyEvent e, DoArson arson1, ATrashCan[] trashCans, AChalkBoard cb, APressurePlate pp, DoMess mess3, DoHack doHack8, DoVendingMachine[] doVendingMachine) {}

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
            if (right && checkRight(t) && (x + width + speed < Main.SCREEN_WIDTH)) {
                x += speed;
            }
            if (left && checkLeft(t) && (x - speed > 0)) {
                x -= speed;
            }
            if (down && checkDown(t) && y + height + speed < Main.SCREEN_HEIGHT) {
                y += speed;
            }
            if (up && checkUp(t) && (y > 155)) {
                y -= speed;
            }
        }

        centerX = (2*x + width)/2;
        centerY = (2*y + height)/2;
    }

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