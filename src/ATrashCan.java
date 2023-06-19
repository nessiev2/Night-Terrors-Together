import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ATrashCan extends Thing {
    private BufferedImage img, imginteract;
    private final static int width = 100;
    private final static int height = 100;
    private boolean isPlayerClose = false;
    private int RADIUS = 50;

    public ATrashCan(int x, int y) {
        super(x, y, width, height);
        try {
            img = ImageIO.read(new File("res\\trash.png"));
            imginteract = ImageIO.read(new File("res\\trashinteract.png"));
        } catch (IOException e) { System.out.println("trash  no image"); }
    }

    public void paint(Graphics g2d, Player p1, Player p2, boolean isTask) {
        isPlayerClose(p1, p2);
        int i = 10;
        if (isPlayerClose && isTask){
            g2d.drawImage(imginteract, getX(), getY(), null);
        }

        g2d.drawImage(img, getX(), getY(), null);
    }

    public void isPlayerClose(Player p1, Player p2) {
        int centerX = (2*getX() + getWidth())/2, centerY = (2*getY() + getHeight())/2;
        double dist1 = Math.sqrt(Math.pow(centerX-p1.getCenterX(), 2) + Math.pow(centerY-p1.getCenterY(), 2));
        double dist2 = Math.sqrt(Math.pow(centerX-p2.getCenterX(), 2) + Math.pow(centerY-p2.getCenterY(), 2));

        if (dist1 <= RADIUS || dist2 <= RADIUS) {
            isPlayerClose = true;
        } else {
            isPlayerClose = false;
        }
    }
}
