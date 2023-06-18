import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ADoor extends Thing {
    private final static int width = 150;
    private final static int height = 200;
    private BufferedImage door;
    private String toClassroom;

    public ADoor(int x, int y, String toClassroom) {
        super(x, y, width, height);
        this.toClassroom = toClassroom;
        try {
            door = ImageIO.read(new File("res\\door.png"));
        } catch (IOException e) { System.out.println("door no image"); }

    }

    public void paint(Graphics g2d) {
        g2d.drawImage(door, getX(), getY(), null);
//        g2d.setColor(Color.yellow);
//        g2d.fillRect(getX(), getY(), width, height);
//        g2d.setColor(new Color(212, 171, 135));
//        g2d.fillRect(getX() + 10, getY() + 10, width - 20, height - 10);
//        g2d.setColor(new Color(74, 74, 74));
//        g2d.fillOval(getX() + width - 40, getY() + 120, 20, 20);
//
        g2d.setColor(Color.black);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g2d.drawString(toClassroom, getX()+30, getY()+150);
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
