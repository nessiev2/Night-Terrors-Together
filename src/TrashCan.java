import java.awt.*;

public class TrashCan extends Thing {
    private final static int width = 100;
    private final static int height = 100;

    private boolean isOnFire = true;
    private boolean playerIsClose = true;

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

    private void setOnFire() {
        isOnFire = true;
    }

}
