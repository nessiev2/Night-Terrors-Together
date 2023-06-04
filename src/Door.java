import java.awt.*;

public class Door extends Thing {
    private final static int width = 150;
    private final static int height = 200;

    public Door(int x, int y) {
        super(x, y, width, height);
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillRect(getX(), getY(), width, height);
        g2d.setColor(new Color(205, 139, 77));
        g2d.fillRect(getX() + 10, getY() + 10, width - 20, height - 10);
    }

    public boolean containsPlayer(Player p1, Player p2) {
        if (p1.getX() >= getX() && p1.getY() >= getY() && p1.getX() + p1.getWidth() <= getX() + width && p1.getY() + p1.getHeight() <= getY() + height) {
            System.out.println("P1 is contained");
            return true;
        } else if (p2.getX() >= getX() && p2.getY() >= getY() && p2.getX() + p2.getWidth() <= getX() + width && p2.getY() + p2.getHeight() <= getY() + height) {
            System.out.println("P2 is contained");
            return true;
        }
        return false;
    }
}
