import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Player {
    protected boolean right = false, left = false, up = false, down = false;
    private int speed = 10;
    private int x;
    private int y;
    private int width = 100;
    private int height = 150;
    private int playern;

    public int getX() {
        return x;
    }

    public int getY() {return y; }

    public Player(int playern) {
        x = 0;
        y = 0;
        this.playern = playern;
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void move(Thing[] t) {
        if (right && (x + width + speed < Main.screenWidth) && checkRight(t)) {
            x += speed;
        }
        if (left && (x - speed >= 0) && checkLeft(t)) {
            x -= speed;
        }
        if (down && (y + height + speed < Main.screenHeight) && checkDown(t)) {
            y += speed;
        }
        if (up && (y - speed >= 0) && checkUp(t)) {
            y -= speed;
        }
    }

    public boolean checkRight(Thing[] things) {
        for (Thing t:things)
            if (x <= t.getX() && x + width + speed >= t.getX() && y + height >= t.getY() && y <= t.getY() + t.getHeight()) {
                return false;
            }
        return true;
    }

    public boolean checkLeft(Thing[] things) {
        for (Thing t:things)
            if (x >= t.getX() && x - speed <= t.getX() + t.getWidth() && y + height >= t.getY() && y <= t.getY() + t.getHeight()) {
                return false;
            }
        return true;
    }

    public boolean checkDown(Thing[] things) {
        for (Thing t:things)
            if (y <= t.getY() && y + height + speed >= t.getY() && x + width >= t.getX() && x <= t.getX() + t.getWidth()) {
                return false;
            }
        return true;
    }

    public boolean checkUp(Thing[] things) {
        for (Thing t:things)
            if (y >= t.getY() && y - speed <= t.getY() + t.getHeight() && x + width >= t.getX() && x <= t.getX() + t.getWidth()) {
                return false;
            }
        return true;
    }



    public void paint(Graphics2D g2d) {
        if (playern == 1)
            g2d.setColor(Color.magenta);
        else
            g2d.setColor(Color.green);
        g2d.fillRect(x, y, width, height);
    }


}
