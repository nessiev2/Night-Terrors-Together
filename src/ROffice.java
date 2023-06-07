import java.awt.*;

public class ROffice {
    DoArson arson1 = new DoArson(false);
    TrashCan[] trashCans = {new TrashCan(1300, 400), new TrashCan(1300, 600), new TrashCan(1000, 600)};

    ChalkBoard cb = new ChalkBoard(900, 10, "OFFICE", false);
    Desk[] desks = {new Desk(0, 500, 500, 250), new Desk(500, 300, 150, 250+200)};
    Wall w1 = new Wall(0, 0);
    PressurePlate pp = new PressurePlate(200, 300);

    public ROffice() {

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

        //jail
        for (int i = 0; i <= 400; i+=50) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(i, 0, 25, 270);
        }

        pp.isPlayerClose(p1, p2);
        pp.paint(g2d);

    }


}
