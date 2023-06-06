import java.awt.*;
import java.util.Random;

public class Sprint extends Task{
    int x, y, radius = 100;
    boolean flag = false;
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
        if (dist1 <= radius && dist2 <= radius){
            flag = true;
        }

        if (flag){
            int i = 0;
            p2.changeSpeed(50);
            p1.changeSpeed(50);
            while (i < 1000){
                i++;
            }
            System.out.println("am sprinting wheeeee");
            //p2.changeSpeed(10);
            //p1.changeSpeed(10);
        }
    }
}
