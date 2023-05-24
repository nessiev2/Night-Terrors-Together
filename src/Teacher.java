import java.awt.*;

public class Teacher {
    private int speed = 5;
    private int x;
    private int y;

    public Teacher() {
        x = 0;
        y = 0;
    }

    public void move(int x1, int y1, int x2, int y2) {
        if (distance(x1, y1, x2, y2) == 1) {
            if (x1 > x) {
                x += speed;
            }
            if (x1 < x) {
                x -= speed;
            }
            if (y1 > y) {
                y += speed;
            }
            if (y1 < y) {
                y -= speed;
            }
        } else {
            if (x2 > x) {
                x += speed;
            }
            if (x2 < x) {
                x -= speed;
            }
            if (y2 > y) {
                y += speed;
            }
            if (y2 < y) {
                y -= speed;
            }
        }
    }


    private int distance(int x1, int y1, int x2, int y2) {
        double dist1 = Math.sqrt(Math.pow(x-x1, 2) + Math.pow(y-y1, 2));
        double dist2 = Math.sqrt(Math.pow(x-x2, 2) + Math.pow(y-y2, 2));

        if (dist1 < dist2)
            return 1;
        return 2;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.fillRect(x, y, 120, 180);
    }


}
