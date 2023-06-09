import java.awt.*;

public class ADesk extends Thing {

    private int width;
    private int height;

    public ADesk(int x, int y) {
        super(x, y, 200, 150);
        width = 200;
        height = 150;
    }

    public ADesk(int x, int y, int w, int h) {
        super(x, y, w, h);
        width = w;
        height = h;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(new Color(150, 75, 0));
        g2d.fillRect(getX(), getY(), width, height);
    }

}
