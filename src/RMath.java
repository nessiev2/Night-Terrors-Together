import java.awt.*;
import java.util.Random;

public class RMath {
    DoArson[] arson7 = new DoArson[3];
    DoArson arsont1, arsont2, arsont3;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = new ADesk[7];
    DoStealTests burnTests7;
    boolean b1;

    public void initializeMath(boolean b, boolean b1, boolean b2){
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(501),r.nextInt(201) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(301) + 660); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson7[0] = arsont1;
        arson7[1] = arsont2;
        arson7[2] = arsont3;

        cb = new AChalkBoard(300, 10, "MATH", b);
        burnTests7 = new DoStealTests(b2);
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

        initializeMath(false, false, false);
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
            tc.paint(g2d, p1, p2, b1);
        }

        for (DoArson arson: arson7){
            arson.paint(g2d, p1, p2, menu);
        }

//        arson7.doTask(p1, p2, menu);

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard
        burnTests7.paint(g2d, p1, p2, menu);
    }


}
