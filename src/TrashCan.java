import java.awt.*;

public class TrashCan extends Thing {
    private final static int width = 100;
    private final static int height = 100;

    private boolean isOnFire = false;
    private boolean playerIsClose = false;

    public TrashCan(int x, int y) {
        super(x, y, width, height);
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

    public void isPlayerClose(Player p1, Player p2) {
        double dist1 = Math.sqrt(Math.pow(getX()-p1.getX(), 2) + Math.pow(getY()-p1.getY(), 2));
        double dist2 = Math.sqrt(Math.pow(getX()-p2.getX(), 2) + Math.pow(getY()-p2.getY(), 2));

        if (dist1 <= 200 || dist2 <= 200) {
            playerIsClose = true;
        } else {
            playerIsClose = false;
        }
    }


    private void setOnFire() {
        isOnFire = true;
    }

}
