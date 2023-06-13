import java.awt.*;
import java.util.Random;

public class RCompSci {
    DoArson arson8;
    DoHack doHack8;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = {new ADesk(400, 500, 200, 100), new ADesk(700, 500, 200, 100), new ADesk(1000, 500, 200, 100), new ADesk(1300, 500, 200, 100), new ADesk(400, 500, 100, 300), new ADesk(1400, 500, 100, 300), new ADesk(750, 800, 400, 200)};
    ADesk[] decorComputers = {new ADesk(410, 650, 50, 80, true), new ADesk(500, 510, 80, 50, true), new ADesk(760, 510, 80, 50, true), new ADesk(1060, 510, 80, 50, true), new ADesk(1320, 510, 80, 50, true), new ADesk(1440, 650, 50, 80, true)};
    public void initializeCompSci(){
        Random r = new Random();
        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(200) + 1700,r.nextInt(221) + 660); //right wall

        arson8 = new DoArson(true);
        doHack8 = new DoHack(true);
        cb = new AChalkBoard(300, 10, "COMPUTER SCIENCE :P", true);
    }

    public RCompSci() {
        initializeCompSci();
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        g2d.setColor(new Color(49, 49, 49));
        for (ADesk c:decorComputers){
            c.paint(g2d);
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
