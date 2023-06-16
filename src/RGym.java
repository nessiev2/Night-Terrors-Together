import java.awt.*;

public class RGym {
    DoArson arson3;
    DoMess mess3;
    ATrashCan[] trashCans = {};
    AChalkBoard cb;
    ADesk[] desks = {};
    AWall w1 = new AWall(0, 0);
    public void initializeGym(boolean b, boolean b1){
        arson3 = new DoArson(true);
        mess3 = new DoMess(true);
        cb = new AChalkBoard(500, 10, "GYM", b);
    }
    public RGym(){
        initializeGym(false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ATrashCan tc: trashCans){
            tc.isPlayerClose(p1, p2);
            tc.paint(g2d);
        }

        mess3.paint(g2d, menu);

        if (p1.getIsSpillingWater()){
            mess3.addWaterStain(p1, g2d);
        }
        if (p2.getIsSpillingWater()){
            mess3.addWaterStain(p2, g2d);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

    }
}
