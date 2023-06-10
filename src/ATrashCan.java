import java.awt.*;

public class ATrashCan extends Thing {
    private final static int width = 100;
    private final static int height = 100;
    private final static int RADIUS = 50;

    private boolean isOnFire;
    private boolean playerIsClose;
    public void initializeTrash(){
        isOnFire = false;
        playerIsClose = false;
    }

    public boolean getIsOnFire() {
        return isOnFire;
    }

    public ATrashCan(int x, int y) {
        super(x, y, width, height);
        initializeTrash();
    }

    public void paint(Graphics g2d) {
        int i = 10;

        // draws the glow around the trash can
        if (playerIsClose) {
            g2d.setColor(Color.yellow);
            g2d.fillOval(getX()-2*i, getY()-2*i, width+4*i, height+4*i);
        }

        // draws the trash can
        g2d.setColor(Color.black);
        g2d.fillOval(getX(), getY(), width, height);
        g2d.setColor(Color.darkGray);
        g2d.fillOval(getX()+i, getY()+i, width-2*i, height-2*i);

        if (isOnFire) {
            g2d.setColor(Color.red);
            g2d.fillRect(getX()+2*i, getY()+2*i, width-4*i, height-4*i);
        }
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

    public void setOnFire() {
        isOnFire = true;
    }

}
