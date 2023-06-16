import java.awt.*;

public class ATrashCan extends Thing {
    private final static int width = 100;
    private final static int height = 100;
    private final static int RADIUS = 50;

    DoArson arson;

    private boolean isOnFire;
    private boolean playerIsClose;
    public void initializeTrash() {
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
        g2d.setColor(Color.black);
        g2d.fillOval(getX(), getY(), width, height);
        g2d.setColor(Color.darkGray);
        g2d.fillOval(getX()+i, getY()+i, width-2*i, height-2*i);
    }



    public void setOnFire() {
        isOnFire = true;
    }

}
