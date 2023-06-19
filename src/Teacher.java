import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Teacher extends Thing {
    private BufferedImage img = null;
    private Image gif = null;
    private int prevX, prevY;
    private boolean right = false, left = false, up = false, down = false, interact = false, standing = false;
    private int speed = 1;
    private boolean bothCaught = false;
    private static final int RADIUS = 400;
    private boolean isFacingLeft = false;
    // this bool represents if u can spawn at this door or NOT
    private boolean d0, d1, d2, d3;

    public void spawnTeacherRandom(Player p1, Player p2) {
        Random r = new Random();
        int tmp1 = r.nextInt(2), tmp2 = r.nextInt(2);
        if (tmp1 == 0){
            tmp1 = -1;
        }
        if (tmp2 == 0){
            tmp2 = -1;
        }

        int x = r.nextInt(200) + 200;
        int y = r.nextInt(200) + 200;

        if (r.nextInt(2) == 0 && !p1.getIsCaught()){
            resetX(p1.getX()+(x*tmp1));
            resetY(p1.getY()+(y*tmp2));
        } else if (!p2.getIsCaught()) {
            resetX(p2.getX()+(x*tmp1));
            resetY(p2.getY()+(y*tmp2));
        } else {
            changeX(200);
            changeY(200);
        }

    }

    public boolean spawnTeacherAtDoor(Player p1, Player p2, String door0, String door1, String door2, String door3) {
        d0 = true;
        d1 = true;
        d2 = true;
        d3 = true;

        String heh = "";
        heh += door0;
        heh += door1;
        heh += door2;
        heh += door3;

        Random r = new Random();

        distanceToDoor(p1, p2);

        char tmp = heh.charAt(r.nextInt(heh.length()));

        boolean temp = false;

        if (tmp == '0') {
            if (d0) {
                resetX(1300);
                resetY(70);
                temp = true;
            }
        } else if (tmp == '1') {
            if (d1) {
                resetX(1770);
                resetY(440);
                temp = true;
            }
        } else if (tmp == '2') {
            if (d2) {
                resetX(1300);
                resetY(840);
                temp = true;
            }
        } else if (tmp == '3'){ // tmp = '3';
            if (d3) {
                resetX(0);
                resetY(440);
                temp = true;
            }
        }
        // true means successfully spawned, false is cannot spawn
        return temp;
    }

    private void distanceToDoor(Player p1, Player p2) {
        // door 1
        double dist0p1 = Math.sqrt(Math.pow(1300-p1.getX(), 2) + Math.pow(70-p1.getY(), 2));
        double dist0p2 = Math.sqrt(Math.pow(1300-p2.getX(), 2) + Math.pow(70-p2.getY(), 2));
        // door 2
        double dist1p1 = Math.sqrt(Math.pow(1770-p1.getX(), 2) + Math.pow(440-p1.getY(), 2));
        double dist1p2 = Math.sqrt(Math.pow(1770-p2.getX(), 2) + Math.pow(440-p2.getY(), 2));
        // door 3
        double dist2p1 = Math.sqrt(Math.pow(1300-p1.getX(), 2) + Math.pow(840-p1.getY(), 2));
        double dist2p2 = Math.sqrt(Math.pow(1300-p2.getX(), 2) + Math.pow(840-p2.getY(), 2));
        // door 4
        double dist3p1 = Math.sqrt(Math.pow(0-p1.getX(), 2) + Math.pow(440-p1.getY(), 2));
        double dist3p2 = Math.sqrt(Math.pow(0-p2.getX(), 2) + Math.pow(440-p2.getY(), 2));

        if (dist0p1 <= RADIUS || dist0p2 <= RADIUS) {
            d0 = false;
        }
        if (dist1p1 <= RADIUS || dist1p1 <= RADIUS) {
            d1 = false;
        }
        if (dist2p1 <= RADIUS || dist2p2 <= RADIUS) {
            d2 = false;
        }
        if (dist3p1 <= RADIUS || dist3p2 <= RADIUS ) {
            d3 = false;
        }
    }

    public boolean getBothCaught(){
        return bothCaught;
    }

    public void initializeTeacher(){
        resetX(0);
        resetY(500);
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

    public void move(NTT c, Player p1, Player p2, int x1, int y1, int x2, int y2) {
        int tmp = distance(p1, p2, x1, y1, x2, y2);

        if (tmp == 1) {
            moveToCloserPlayer(x1, y1);
        } else if (tmp == -1) {
            bothCaught = true;
        } else {
            moveToCloserPlayer(x2, y2);
        }

        prevX = getX();
        prevY = getY();

    }

    private void moveToCloserPlayer(int x1, int y1) {
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
        if (!standing){
            if (isFacingLeft) {
                g2d.drawImage(gif, getX() + getWidth(), getY(), -getWidth(), getHeight(), null);
            } else {
                g2d.drawImage(gif, getX(), getY(), getWidth(), getHeight(), null);
            }
        } else{
            if (isFacingLeft) {
                g2d.drawImage(img, getX() + getWidth(), getY(), -getWidth(), getHeight(), null);
            } else {
                g2d.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
            }
        }

    }

}
