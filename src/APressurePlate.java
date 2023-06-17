import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class APressurePlate extends Thing {
    private BufferedImage img, imginteract;
    private static int width = 100;
    private static int height = 100;
    private final static int RADIUS = 50;

    private boolean playerIsClose = false;

    public APressurePlate(int x, int y) {
        super(x, y, width, height);
        try {
            img = ImageIO.read(new File("res\\pp.png"));
            imginteract = ImageIO.read(new File("res\\ppinteract.png"));
        } catch (IOException e) { System.out.println("pp no image"); }
    }

    public void paint(Graphics g2d) {
        int i = 10;
        if (playerIsClose) {
            g2d.drawImage(imginteract, getX()-i, getY()-i, null);
//            g2d.setColor(Color.yellow);
//            g2d.fillRect(getX()-2*i, getY()-2*i, width+4*i, height+4*i);
        }

//        g2d.setColor(Color.cyan);
//        g2d.fillRect(getX(), getY(), width, height);
        g2d.drawImage(img, getX(), getY(), null);
    }

    public boolean isPlayerClose(Player p1, Player p2) {
        int centerX = (int)((2*getX() + getWidth())/2), centerY = (int)((2*getY() + getHeight())/2);
        double dist1 = Math.sqrt(Math.pow(centerX-p1.getCenterX(), 2) + Math.pow(centerY-p1.getCenterY(), 2));
        double dist2 = Math.sqrt(Math.pow(centerX-p2.getCenterX(), 2) + Math.pow(centerY-p2.getCenterY(), 2));

        if (dist1 <= RADIUS || dist2 <= RADIUS) {
            playerIsClose = true;
            return true;
        } else {
            playerIsClose = false;
            return false;
        }
    }

}
