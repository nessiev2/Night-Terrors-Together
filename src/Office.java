import java.awt.*;

public class Office {
    Arson arson1 = new Arson(true);
    TrashCan[] trashCans = {new TrashCan(1300, 400), new TrashCan(1300, 600), new TrashCan(1000, 600)};

    ChalkBoard cb = new ChalkBoard(300, 10, "OFFICE");
    Desk[] desks = {};
    Wall w1 = new Wall(0, 0);
    public Office() {

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

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard

    }


}
