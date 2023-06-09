import java.awt.*;

public class ROffice {
    DoArson arson1 = new DoArson(false);
    ATrashCan[] trashCans = {new ATrashCan(1300, 400), new ATrashCan(1300, 600), new ATrashCan(1000, 600)};

    AChalkBoard cb = new AChalkBoard(900, 10, "OFFICE", false);
    ADesk[] desks = {new ADesk(0, 500, 500, 250), new ADesk(500, 300, 150, 250+200)};
    AWall w1 = new AWall(0, 0);
    APressurePlate pp = new APressurePlate(200, 300);

    public ROffice() {

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

        for (ADesk d:desks) {
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
