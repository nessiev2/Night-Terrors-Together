import java.awt.*;
import java.util.Deque;
import java.util.Random;

public class RMath {
    DoArson arson7;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = new ADesk[7];
    DoBurnTests burnTests7;

    public void initializeMath(){
        Random r = new Random();

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall

        arson7 = new DoArson(true);
        cb = new AChalkBoard(300, 10, "MATH", true);
        burnTests7 = new DoBurnTests(true);
    }

    public RMath() {
        int counter = 0;

        //generating desks loop
        for (int i = 400; i <= 1200; i += 400){
            for (int j = 600; j <= 900; j += 300){
                desks[counter] = new ADesk(i, j);
                counter++;
            }
        }
        desks[6] = new ADesk(600, 300, 400, 150);

        initializeMath();
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        // IRENE IS MAKING A CLASSROOM DESK TRIAL
        for (ADesk d:desks) {
            d.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        arson7.doTask(trashCans, p1, p2, menu);

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard
        burnTests7.paint(g2d, p1, p2);
    }


}
