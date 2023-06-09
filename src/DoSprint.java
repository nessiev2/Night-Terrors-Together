import java.awt.*;
import java.util.Random;

public class DoSprint extends Task{
    int x, y, i = 0, j = 0, radius = 100;
    boolean flag1 = false, flag2 = false, isComplete = false;
    Timer t = new Timer();
    public boolean getFlag1(){
        return flag1;
    }
    public boolean getFlag2(){
        return flag2;
    }
    public DoSprint(boolean isTask) {
        super(isTask);

        Random r = new Random();
        // i <3 hard-coding
        int[] randX = {r.nextInt(1431) + 270, r.nextInt(271), r.nextInt(221) + 1420};
        int[] randY = {r.nextInt(91) + 290, r.nextInt(221) + 660, r.nextInt(221) + 660};

        x = randX[r.nextInt(3)];
        y = randY[r.nextInt(3)];
    }
    public int sprintTicks(Player p){
        if (p.getPlayerN() == 1){
            i++;
        } else {
            j++;
        }

        if (i >= 300){
            flag1 = false;
            p.changeSpeed(10);
            i = 0;
        }

        if (j >= 300){
            flag1 = false;
            p.changeSpeed(10);
            j = 0;
        }

        if (p.getPlayerN() == 1){
            return i;
        }
        return j;
    }
    public void paint(Graphics g2d, Player p1, Player p2) {
        double dist1 = Math.sqrt(Math.pow(p1.getX()-x, 2) + Math.pow(p1.getY()-y, 2));
        double dist2 = Math.sqrt(Math.pow(p2.getX()-x, 2) + Math.pow(p2.getY()-y, 2));

        if ((dist1 <= radius || dist2 <= radius) && !isComplete){
            g2d.setColor(Color.yellow);
            g2d.fillOval(x, y, radius/2, radius/2);
        }
        if (dist1 <= radius && !isComplete){
            flag1 = true;
            p1.changeSpeed(20);
        }
        if (dist2 <= radius && !isComplete){
            flag2 = true;
            p2.changeSpeed(20);
        }

        if (flag1 && flag2){
            isComplete = true;
        }
    }
}
