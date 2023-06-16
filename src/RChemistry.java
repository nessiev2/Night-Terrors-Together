import java.awt.*;
import java.util.Random;

public class RChemistry {
    DoArson arson6;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = {new ADesk(400, 500), new ADesk(800, 500), new ADesk(400, 800), new ADesk(800, 800)};
    DoBadReaction badReaction6;
    public void initializeChem(boolean b, boolean b1){
        Random r = new Random();

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall

        arson6 = new DoArson(true);
        cb = new AChalkBoard(300, 10, "CHEMISTRY", b);
        badReaction6 = new DoBadReaction(true);
    }

    public RChemistry() {
        initializeChem(false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        badReaction6.doTask(p1, p2);

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        badReaction6.paint(g2d, menu);

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

    }


}
