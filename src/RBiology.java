import java.awt.*;
import java.util.Random;

public class RBiology {
    DoArson[] arson4 = new DoArson[3];
    DoArson arsont1, arsont2, arsont3;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = new ADesk[6];
    DoDissection dissection4;
    public void initializeBio(boolean b, boolean b1){
        Random r = new Random();

        trashCans[0] = new ATrashCan(r.nextInt(1001) + 400,r.nextInt(131) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(150) + 270); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1450,r.nextInt(451) + 300); //right wall

        arsont1 = new DoArson(true, trashCans[0]);
        arsont2 = new DoArson(true, trashCans[1]);
        arsont3 = new DoArson(true, trashCans[2]);

        arson4[0] = arsont1;
        arson4[1] = arsont2;
        arson4[2] = arsont3;

        cb = new AChalkBoard(300, 10, "BIOLOGY",b);
        dissection4 = new DoDissection(true);
    }
    public RBiology() {
        int counter = 0;

        //generating desks loop
        for (int i = 700; i <= 1000; i += 300){         // x
            for (int j = 500; j <= 800; j += 300){      // y
                desks[counter] = new ADesk(i, j);
                counter++;
            }
        }

        desks[4] = new ADesk(100, 520, 150, 400);
        desks[5] = new ADesk(400, 520, 150, 400);

        initializeBio(false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2);
        }

        for (DoArson arson: arson4){
            arson.paint(g2d, p1, p2, menu);
        }

        w1.paint(g2d); // wall

        dissection4.paint(g2d, p1, p2, menu);

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

//        g2d.fillRect(400, 290, 1000, 130);   // top
//        g2d.fillRect(0, 270, 270, 150);     // left
//        g2d.fillRect(1450, 300, 220, 450);  // right
    }
}
