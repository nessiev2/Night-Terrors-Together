import java.awt.*;

public class TrashCan extends Thing {
    private final static int width = 100;
    private final static int height = 100;

    public TrashCan(int x, int y) {
        super(x, y, width, height);
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.black);
        g2d.fillOval(getX(), getY(), width, height);
        int i = 10;
        g2d.setColor(Color.darkGray);
        g2d.fillOval(getX()+i, getY()+i, width-2*i, height-2*i);
    }
}
