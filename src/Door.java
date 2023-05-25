import java.awt.*;

public class Door extends Thing {
    private final static int width = 150;
    private final static int height = 200;

    public Door(int x, int y) {
        super(x, y, width, height);
    }

    public int getDoorHeight(){
        return height;
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillRect(getX(), getY(), width, height);
    }
}
