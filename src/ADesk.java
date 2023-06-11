import java.awt.*;

public class ADesk extends Thing {

    private int width;
    private int height;
    boolean diffColour = false;

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
    public ADesk(int x, int y, int w, int h, boolean diffColour) {
        super(x, y, w, h);
        width = w;
        height = h;
        this.diffColour = diffColour;
    }

    public void paint(Graphics g2d) {
        if (!diffColour){
            g2d.setColor(new Color(150, 75, 0));
        }
        g2d.fillRect(getX(), getY(), width, height);
    }

}
