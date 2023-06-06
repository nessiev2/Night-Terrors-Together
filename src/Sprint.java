import java.awt.*;
import java.util.Random;

public class Sprint extends Task{
    int x, y, radius = 100;
    boolean isComplete = false, p1Sprint = false, p2Sprint = false;
    Timer t = new Timer();
    public Sprint(boolean isTask) {
        super(isTask);

        Random r = new Random();
        // i <3 hard-coding
        int[] randX = {r.nextInt(1431) + 270, r.nextInt(271), r.nextInt(221) + 1420};
        int[] randY = {r.nextInt(91) + 290, r.nextInt(221) + 660, r.nextInt(221) + 660};

        x = randX[r.nextInt(3)];
        y = randY[r.nextInt(3)];
    }

    public void paint(Graphics g2d, Player p1, Player p2) {
        double dist1 = Math.sqrt(Math.pow(p1.getX()-x, 2) + Math.pow(p1.getY()-y, 2));
        double dist2 = Math.sqrt(Math.pow(p2.getX()-x, 2) + Math.pow(p2.getY()-y, 2));

        //if (dist1 <= radius || dist2 <= radius){
            g2d.setColor(Color.yellow);
            g2d.fillOval(x, y, radius/2, radius/2);
        //}
        if (dist1 <= radius && !isComplete){
            if (dist2 <= radius){
                p2Sprint = true;
            }
        }

        if (dist2 <= radius && !isComplete){
            if (dist1 <= radius){
                p1Sprint = true;
            }
        }

        if (p1Sprint){
            p1.changeSpeed(20);
        } else {
            p1.changeSpeed(10);
        }
        if (p2Sprint){
            p2.changeSpeed(20);
        } else {
            p2.changeSpeed(10);
        }

        if (p1Sprint && p2Sprint){
            isComplete = true;
            p1Sprint = false;
            p2Sprint = false;
            System.out.println("DONE!!!!");
        }
    }
}
