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
        x = 500;
        y = 500;
        this.playern = playern;
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void move() {

        if (right && (x + width + speed < Main.screenWidth)) {
            x += speed;
        }
        if (left && (x - speed >= 0)) {
            x -= speed;
        }
        if (down && (y + height + speed < Main.screenHeight)) {
            y += speed;
        }
        if (up && (y - speed >= 0)) {
            y -= speed;
        }
    }

    public void paint(Graphics2D g2d) {
        if (playern == 1)
            g2d.setColor(Color.magenta);
        else
            g2d.setColor(Color.green);
        g2d.fillRect(x, y, width, height);
    }


}
