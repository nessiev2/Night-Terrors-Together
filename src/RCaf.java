import java.awt.*;

public class RCaf {
    DoArson arson2;
    ATrashCan[] trashCans = {};
    AChalkBoard cb = new AChalkBoard(500, 10, "CAFETERIA", false);
    ADesk[] desks = {new ADesk(300, 300, 400, 150, true), new ADesk(800, 300, 400, 150, true), new ADesk(1300, 300, 400, 150, true), new ADesk(300, 600, 400, 150, true), new ADesk(800, 600, 400, 150, true), new ADesk(1300, 600, 400, 150, true)};
    AWall w1 = new AWall(0, 0);
    public void initializeCaf(){
        arson2 = new DoArson(true);
    }
    public RCaf() {
        initializeCaf();
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

        g2d.setColor(new Color(160, 146, 146));
        for (ADesk d:desks) {
            d.paint(g2d);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard
    }


}
