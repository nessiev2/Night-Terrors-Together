import java.awt.*;
import java.util.Random;

public class RPhysics {
    Arson arson5 = new Arson(true);
    TrashCan[] trashCans = new TrashCan[3];
    ChalkBoard cb = new ChalkBoard(300, 10, "PHYSICS", true);
    Wall w1 = new Wall(0, 0);
    Desk[] desks = new Desk[6];
    Sprint sprint = new Sprint(true);

    public RPhysics() {
        Random r = new Random();
        int counter = 0;

        //generating desks loop
        for (int i = 400; i <= 1000; i += 300){         // x
            for (int j = 500; j <= 800; j += 300){      // y
                desks[counter] = new Desk(i, j);
                counter++;
            }
        }

        trashCans[0] = new TrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new TrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new TrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        // IRENE IS MAKING A CLASSROOM DESK TRIAL
        for (Desk d:desks) {
            d.paint(g2d);
        }

        for (TrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard

        sprint.paint(g2d, p1, p2);
    }


}
