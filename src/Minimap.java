import java.awt.*;

public class Minimap {
    int x = 1750, y = 20, w = 60;

    public Minimap() {}

    public void paint (Graphics2D g2d){
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, w, w);
        g2d.setColor(Color.black);
        g2d.drawLine(x+20, y, x+20, y+w);
        g2d.drawLine(x+40, y, x+40, y+w);
        g2d.drawLine(x, y+20, x+w, y+20);
        g2d.drawLine(x, y+40, x+w, y+40);
    }

    public void paintYou(Graphics2D g2d, int i) {
        int ya = (i-1) / 3;
        int xa = (i%3);
        if (xa == 0) {
            xa = 2;
        } else {
            xa -= 1;
        }
        //System.out.println("xa " + xa + " ya " + ya);
        g2d.setColor(Color.pink);
        g2d.fillOval(x+xa*20, y+ya*20, 20, 20);
    }

    public void paintTeacher(Graphics2D g2d, int i) {
        int ya = (i-1) / 3;
        int xa = (i%3);
        if (xa == 0) {
            xa = 2;
        } else {
            xa -= 1;
        }
        //System.out.println("xa " + xa + " ya " + ya);
        g2d.setColor(Color.blue);
        g2d.fillOval(x+xa*20, y+ya*20, 20, 20);
    }


}
