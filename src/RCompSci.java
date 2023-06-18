import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class RCompSci {
    DoArson[] arson8 = new DoArson[3];
    DoArson arsont1, arsont2, arsont3;
    DoHack doHack8;
    ATrashCan[] trashCans = new ATrashCan[3];
    AChalkBoard cb;
    AWall w1 = new AWall(0, 0);
    ADesk[] desks = {new ADesk(400, 500, 200, 100), new ADesk(700, 500, 200, 100), new ADesk(1000, 500, 200, 100), new ADesk(1300, 500, 200, 100), new ADesk(400, 500, 100, 300), new ADesk(1400, 500, 100, 300), new ADesk(750, 800, 400, 200)};
    ADesk[] decorComputers = {new ADesk(410, 650, 50, 80, true), new ADesk(500, 510, 80, 50, true), new ADesk(760, 510, 80, 50, true), new ADesk(1060, 510, 80, 50, true), new ADesk(1320, 510, 80, 50, true), new ADesk(1440, 650, 50, 80, true)};
    boolean b1;

    public void initializeCompSci(boolean b, boolean b1, boolean b2){
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(100) + 1700,r.nextInt(221) + 660); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson8[0] = arsont1;
        arson8[1] = arsont2;
        arson8[2] = arsont3;

        doHack8 = new DoHack(b2);
        cb = new AChalkBoard(300, 10, "COMPUTER SCIENCE :P", b);
    }

    public RCompSci() {
        initializeCompSci(false, false,false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        g2d.setColor(new Color(49, 49, 49));
        for (ADesk c:decorComputers){
            c.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        for (DoArson arson: arson8){
            arson.paint(g2d, p1, p2, menu);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

        doHack8.paint(g2d, p1, p2, menu);
    }


}
