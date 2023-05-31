import java.awt.*;

public class Teacher extends Thing {
    private int speed = 2;

    public Teacher() {
        super(0, 0, 120, 180);
    }

    public void move(Player p1, Player p2, int x1, int y1, int x2, int y2) {
        int tmp = distance(p1, p2, x1, y1, x2, y2);
        if (tmp == 1) {
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
        } else if (tmp == -1){
            System.out.println("GAMEOVER LMAO U LOST LOSER");
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


    private int distance(Player p1, Player p2, int x1, int y1, int x2, int y2) {
        double dist1 = Math.sqrt(Math.pow(getX()-x1, 2) + Math.pow(getY()-y1, 2));
        double dist2 = Math.sqrt(Math.pow(getX()-x2, 2) + Math.pow(getY()-y2, 2));

        if (p1.getIsCaught() && p2.getIsCaught())
            return -1;
        else if (p2.getIsCaught() || (!p1.getIsCaught() && !p2.getIsCaught()) && dist1 < dist2)
            return 1;
        if (p1.getIsCaught() && p2.getIsCaught())
            return -1;
        return 2;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.fillRect(getX(), getY(), 120, 180);
    }


}
