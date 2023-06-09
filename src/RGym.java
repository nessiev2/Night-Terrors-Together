import java.awt.*;

public class RGym {
    DoArson arson3 = new DoArson(true);
    ATrashCan[] trashCans = {};

    AChalkBoard cb = new AChalkBoard(500, 10, "GYM", true);
    ADesk[] desks = {};
    AWall w1 = new AWall(0, 0);
    public RGym() {

    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ATrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard
    }
}
