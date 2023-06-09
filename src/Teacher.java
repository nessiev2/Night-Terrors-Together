import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Teacher extends Thing {
    private BufferedImage img = null;
    private Image gif = null;
    private boolean right = false, left = false, up = false, down = false, interact = false;
    private int speed = 1;
    private boolean bothCaught = false;

    private boolean isFacingLeft = false;
    public void changeFaceDirection(boolean b) { isFacingLeft = b; }

    public void spawnTeacher(int x, int y) {
        resetX(x);
        resetY(y);
    }
    public boolean getBothCaught(){
        return bothCaught;
    }

    public void initializeTeacher(){
        spawnTeacher(0, 0);
        bothCaught = false;
    }
    public Teacher() {
        super(0, 0, 120, 180);
        try {
                img = ImageIO.read(new File("res\\TeacherWalkR.png"));
                gif = Toolkit.getDefaultToolkit().createImage("res\\TeacherWalkGif.gif");
            } catch (IOException e) { System.out.println("teacher problem"); }
        initializeTeacher();
    }

    public void move(Main c, Player p1, Player p2, int x1, int y1, int x2, int y2) {
        int tmp = distance(p1, p2, x1, y1, x2, y2);

        if (tmp == 1) {
            if (x1 > getX()) {
                changeX(speed);
                right = true;
                isFacingLeft = false;
            }
            if (x1 < getX()) {
                changeX(-speed);
                left = true;
                isFacingLeft = true;
            }
            if (y1 > getY()) {
                changeY(speed);
                down = true;
            }
            if (y1 < getY()) {
                changeY(-speed);
                up = true;
            }
        } else if (tmp == -1) {
            bothCaught = true;
        } else {
            if (x2 > getX()) {
                changeX(speed);
                right = true;
                isFacingLeft = false;
            }
            if (x2 < getX()) {
                changeX(-speed);
                left = true;
                isFacingLeft = true;
            }
            if (y2 > getY()) {
                changeY(speed);
                down = true;
            }
            if (y2 < getY()) {
                changeY(-speed);
                up = true;
            }
        }

    }

    private int distance(Player p1, Player p2, int x1, int y1, int x2, int y2) {
        double dist1 = Math.sqrt(Math.pow(getX()-x1, 2) + Math.pow(getY()-y1, 2));
        double dist2 = Math.sqrt(Math.pow(getX()-x2, 2) + Math.pow(getY()-y2, 2));

        if (p1.getIsCaught() && p2.getIsCaught())
            return -1;
        else if (p2.getIsCaught() || (!p1.getIsCaught() && !p2.getIsCaught()) && dist1 < dist2)
            return 1;
        return 2;
    }

    public void paint(Graphics2D g2d) {
        if (isFacingLeft) {
            //g2d.drawImage(img, getX(), getY(), width, height, null);
            g2d.drawImage(gif, getX() + getWidth(), getY(), -getWidth(), getHeight(), null);
        } else {
            //g2d.drawImage(img, getX() + getWidth(), getY(), -width, height, null);
            g2d.drawImage(gif, getX(), getY(), getWidth(), getHeight(), null);
        }
    }

}
