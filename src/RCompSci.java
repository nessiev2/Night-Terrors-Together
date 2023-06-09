import java.awt.*;
import java.util.Random;

public class RCompSci {
    DoArson arson8 = new DoArson(true);
    DoHack doHack8 = new DoHack(true);
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb = new AChalkBoard(300, 10, "COMPUTER SCIENCE :P", true);
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = {new ADesk(400, 500, 200, 100), new ADesk(700, 500, 200, 100), new ADesk(1000, 500, 200, 100), new ADesk(1300, 500, 200, 100), new ADesk(400, 600, 100, 200), new ADesk(1400, 600, 100, 200), new ADesk(750, 800, 400, 200)};

    public RCompSci() {
        Random r = new Random();
        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1) {
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

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard

        doHack8.paint(g2d, p1, p2);
    }


}
