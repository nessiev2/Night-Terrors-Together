import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Player {
    private BufferedImage img = null;
    private Image gif = null;
    protected boolean right = false, left = false, up = false, down = false, interact = false;
    private int speed = 10;
    private int x, centerX;
    private int y, centerY;
    private int width = 80;
    private int height = 120;
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

    public void changeIsCaught(boolean c) {
        isCaught = c;
    }
    public int getPlayerSpeed() { return speed; }
    public void changeSpeed(int x) {speed = x; }


    public Player(int playerN) {
        x = 500;
        y = 500;
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

    public void paint(Graphics2D g2d) {
        if (left || isFacingLeft) {
            //g2d.drawImage(img, getX(), getY(), width, height, null);
            g2d.drawImage(gif, getX()+width, getY(), -width, height, null);
        } else {
            //g2d.drawImage(img, getX() + getWidth(), getY(), -width, height, null);
            g2d.drawImage(gif, getX(), getY(), width, height, null);
        }
    }

    public void spawnPlayer(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(Player p2, KeyEvent e, DoArson arson1, TrashCan[] trashCans, ChalkBoard cb, PressurePlate pp) {}

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

        centerX = (int)((2*x + width)/2);
        centerY = (int)((2*y + height)/2);
    }

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