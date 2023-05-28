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
    }

    public boolean containsPlayer(Player p1, Player p2) {
        /*System.out.println("p right: " + (p.getX()+p.getWidth()));
        System.out.println("door right: " + (getX() + width));

        System.out.println("p bottom: " + (p.getY()+p.getHeight()));
        System.out.println("door bottom: " + (getY() + height));
*/


        if (p1.getX() + p1.getWidth() <= getX() + width && p1.getY() + p1.getHeight() <= getY() + height) {
            System.out.println("P1 is contained");
            return true;
        } else if (p2.getX() + p2.getWidth() <= getX() + width && p2.getY() + p2.getHeight() <= getY() + height) {
            System.out.println("P2 is contained");
            return true;
        }
        return false;
    }
}
