import java.awt.*;

public class ADoor extends Thing {
    private final static int width = 150;
    private final static int height = 200;

    public ADoor(int x, int y) {
        super(x, y, width, height);
    }

    public void paint(Graphics g2d) {
        g2d.setColor(Color.yellow);
        g2d.fillRect(getX(), getY(), width, height);
        g2d.setColor(new Color(212, 171, 135));
        g2d.fillRect(getX() + 10, getY() + 10, width - 20, height - 10);
        g2d.setColor(new Color(74, 74, 74));
        g2d.fillOval(getX() + width - 40, getY() + 120, 20, 20);
    }

    public boolean containsPlayer(Player p1, Player p2) {
        if (p1.getX() >= getX() && p1.getY() >= getY() && p1.getX() + p1.getWidth() <= getX() + width && p1.getY() + p1.getHeight() - 20 <= getY() + height) {
            return true;
        } else if (p2.getX() >= getX() && p2.getY() >= getY() && p2.getX() + p2.getWidth() <= getX() + width && p2.getY() + p2.getHeight() - 20 <= getY() + height) {
            return true;
        }
        return false;
    }
}
