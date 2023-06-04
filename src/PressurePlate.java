import java.awt.*;

public class PressurePlate extends Thing {

    private static int width = 100;
    private static int height = 100;
    private final static int RADIUS = 50;

    private boolean playerIsClose = false;

    public PressurePlate(int x, int y) {
        super(x, y, width, height);
    }

    public void paint(Graphics g2d) {
        int i = 10;
        if (playerIsClose) {
            g2d.setColor(Color.yellow);
            g2d.fillRect(getX()-2*i, getY()-2*i, width+4*i, height+4*i);
        }

        g2d.setColor(Color.cyan);
        g2d.fillRect(getX(), getY(), width, height);
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