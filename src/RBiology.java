import java.awt.*;
import java.util.Random;

public class RBiology {
    DoArson arson4;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = new ADesk[6];
    DoDissection dissection4;
    public void initializeBio(){
        Random r = new Random();

//        g2d.fillRect(400, 290, 1000, 130);   // top
//        g2d.fillRect(0, 260, 270, 220);     // left
//        g2d.fillRect(1450, 300, 220, 450);  // right

        trashCans[0] = new ATrashCan(r.nextInt(1001) + 400,r.nextInt(131) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 260); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1450,r.nextInt(451) + 300); //right wall

        arson4 = new DoArson(true);
        cb = new AChalkBoard(300, 10, "BIOLOGY",true);
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

        initializeBio();
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        w1.paint(g2d); // wall

        dissection4.paint(g2d);

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard

//        g2d.fillRect(400, 290, 1000, 130);   // top
//        g2d.fillRect(0, 260, 270, 220);     // left
//        g2d.fillRect(1450, 300, 220, 450);  // right
    }
}
