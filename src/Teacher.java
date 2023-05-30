import java.awt.*;

public class Teacher extends Thing {
    private int speed = 5;

    public Teacher() {
        super(0, 0, 120, 180);
    }

    public void move(int x1, int y1, int x2, int y2) {
        if (distance(x1, y1, x2, y2) == 1) {
            if (x1 > getX()) {
                changeX(speed);
            }
            if (x1 < getX()) {
                changeX(-speed);
            }
            if (y1 > getY()) {
                changeY(speed);
            }
            if (y1 < getY()) {
                changeY(-speed);
            }
        } else {
            if (x2 > getX()) {
                changeX(speed);
            }
            if (x2 < getX()) {
                changeX(-speed);
            }
            if (y2 > getY()) {
                changeY(speed);
            }
            if (y2 < getY()) {
                changeY(-speed);
            }
        }

    }


    private int distance(int x1, int y1, int x2, int y2) {
        double dist1 = Math.sqrt(Math.pow(getX()-x1, 2) + Math.pow(getY()-y1, 2));
        double dist2 = Math.sqrt(Math.pow(getX()-x2, 2) + Math.pow(getY()-y2, 2));

        if (dist1 < dist2)
            return 1;
        return 2;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.fillRect(getX(), getY(), 120, 180);
    }


}
