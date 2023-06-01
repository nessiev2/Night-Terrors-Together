import java.awt.*;

public class RCaf {
    Arson arson2 = new Arson(true);
    TrashCan[] trashCans = {};

    ChalkBoard cb = new ChalkBoard(500, 10, "CAFETERIA");
    Desk[] desks = {new Desk(70, 500, 400, 200)};
    Wall w1 = new Wall(0, 0);
    public RCaf() {

    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (TrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        for (Desk d:desks) {
            d.paint(g2d);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard
    }


}
