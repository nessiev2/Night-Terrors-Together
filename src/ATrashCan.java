import java.awt.*;

public class ATrashCan extends Thing {
    private final static int width = 100;
    private final static int height = 100;
    private boolean isPlayerClose = false;
    private int RADIUS = 50;

    public ATrashCan(int x, int y) {
        super(x, y, width, height);
    }

    public void paint(Graphics g2d, Player p1, Player p2, boolean isTask) {
        int i = 10;
        isPlayerClose(p1, p2);
        if (isPlayerClose && isTask){
            g2d.setColor(Color.yellow);
            g2d.fillOval(getX()-2*i, getY()-2*i, getWidth()+4*i, getHeight()+4*i);
        }

        g2d.setColor(Color.black);
        g2d.fillOval(getX(), getY(), width, height);
        g2d.setColor(Color.darkGray);
        g2d.fillOval(getX()+i, getY()+i, width-2*i, height-2*i);
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
