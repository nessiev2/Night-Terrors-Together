import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Physics {
    Arson arson1 = new Arson(true);
    TrashCan[] trashCans = {new TrashCan(1000, 400), new TrashCan(1300, 400), new TrashCan(1000, 600)};

    ChalkBoard cb = new ChalkBoard(300, 10);

    Wall w1 = new Wall(0, 0);
    Desk[] desks = {new Desk(200, 300), new Desk(600, 300), new Desk(200, 700), new Desk(600, 700)};
    Door d1 = new Door(0, w1.getWallHeight()-200);

    public Physics() {

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
        d1.paint(g2d); // door

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d); // chalkboard

        /*
        p1.paint(g2d);
        p2.paint(g2d);
        t.paint(g2d);
        */
        //cd.paint(g2d);

        if (d1.containsPlayer(p1, p2)) {
            System.out.println("transition black screen worked");
            transition1.paint(g2d);
        }
    }


}
