import java.awt.*;

public class Desk extends Thing {

    private final static int width = 200;
    private final static int height = 150;

    public Desk(int x, int y) {
        super(x, y, width, height);
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.lightGray);
        g2d.fillRect(getX(), getY(), width, height);
    }

}
