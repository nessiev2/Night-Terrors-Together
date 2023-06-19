import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class DoSprint extends Task{
    private boolean ts = true;

    private Random r = new Random();
    private int x, y, i = 0, j = 0, radius = 100;
    private boolean flag1 = false, flag2 = false, isComplete = false;
    private int[] randX = {r.nextInt(1431) + 270, r.nextInt(271), r.nextInt(221) + 1420}, randY = {r.nextInt(91) + 290, r.nextInt(221) + 660, r.nextInt(221) + 660};

    public boolean getFlag1(){
        return flag1;
    }
    public boolean getFlag2(){
        return flag2;
    }
    public void initializeSprint(){
        ts = true;
        System.out.println("reset sprint");
        x = 0;
        y = 0;
        i = 0;
        j = 0;


        flag1 = false;
        flag2 = false;
        isComplete = false;

        int yipee = r.nextInt(3);
        x = randX[yipee];
        y = randY[yipee];
    }

    public void resetNormalSpeed(Player p1, Player p2){
        p1.changeSpeed(10);
        p2.changeSpeed(10);
    }

    public DoSprint(boolean isTask) {
        super(isTask);
        initializeSprint();
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
            flag2 = false;
            p.changeSpeed(10);
            j = 0;
        }

        if (p.getPlayerN() == 1){
            return i;
        }
        return j;
    }
    public void paint(Graphics g2d, Player p1, Player p2, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        double dist1 = Math.sqrt(Math.pow(p1.getX()-x, 2) + Math.pow(p1.getY()-y, 2));
        double dist2 = Math.sqrt(Math.pow(p2.getX()-x, 2) + Math.pow(p2.getY()-y, 2));

        if (isTask) {
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

            if (flag1 && flag2 && !isComplete){
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
                g2d.setColor(Color.black);
                isComplete = true;
                if (ts) {
                    menu.updateTaskCompletion(6);
                    ts = false;
                }
                //System.out.println("do sprint COMPLETE");
            }
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));

            if (isComplete){
                g2d.setColor(Color.white);
                g2d.drawString("task complete!", 650, 200);
            } else {
                g2d.setColor(Color.white);
                g2d.drawString("find the yellow circle on the ground", 400, 200);
                g2d.drawString("and both players sprint to complete the task", 400, 200+25);
            }
        }
    }
}
