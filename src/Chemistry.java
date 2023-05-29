import java.awt.*;

public class Chemistry {
    Arson arson6 = new Arson(true);
    TrashCan[] trashCans = {new TrashCan(1300, 400), new TrashCan(1300, 600), new TrashCan(1000, 600)};

    ChalkBoard cb = new ChalkBoard(300, 10, "CHEMISTRY");

    Wall w1 = new Wall(0, 0);
    Desk[] desks = {new Desk(200, 500), new Desk(600, 500), new Desk(200, 800), new Desk(600, 800)};

    public Chemistry() {

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

    }


}
