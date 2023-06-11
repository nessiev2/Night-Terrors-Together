import java.awt.*;
import java.util.Random;

public class ROffice {
    DoArson arson1 = new DoArson(false);
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb = new AChalkBoard(900, 10, "OFFICE", false);
    ADesk[] desks = {new ADesk(0, 500, 500, 250), new ADesk(500, 300, 150, 250+200)};
    AWall w1 = new AWall(0, 0);
    APressurePlate pp = new APressurePlate(200, 300);

    public void initializeOffice(){
        Random r = new Random();

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall
    }
    public ROffice(){
        initializeOffice();
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

        pp.isPlayerClose(p1, p2);
        pp.paint(g2d);

        g2d.setColor(new Color(205, 255, 255));
        g2d.fillRect(30, 20, 360, 230);

        paintBars(g2d);
    }

    public void paintBars(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.lightGray);
        g2d.fillRect(0, 250, 400, 20);

        g2d.setColor(new Color(86, 86, 86));
        for (int i = 0; i <= 400; i+=50) {
            g2d.fillRect(i, 0, 15, 270);
        }
    }


}
