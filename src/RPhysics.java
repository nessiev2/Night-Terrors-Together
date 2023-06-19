import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class RPhysics {
    private DoArson[] arson5 = new DoArson[3];
    private DoArson arsont1, arsont2, arsont3;
    private ATrashCan[] trashCans = new ATrashCan[3];
    private AChalkBoard cb;
    private AWall w1 = new AWall(0, 0);
    private ADesk[] desks = new ADesk[6];

    private boolean b1;

    public ADesk[] getDesks(){
        return desks;
    }
    public DoArson[] getArson(){
        return arson5;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }

    public void initializePhysics(boolean b, boolean b1){
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson5[0] = arsont1;
        arson5[1] = arsont2;
        arson5[2] = arsont3;

        cb = new AChalkBoard(300, 10, "PHYSICS", b);
    }

    public RPhysics() {
        int counter = 0;

        //generating desks loop
        for (int i = 400; i <= 1000; i += 300){         // x
            for (int j = 500; j <= 800; j += 300){      // y
                desks[counter] = new ADesk(i, j);
                counter++;
            }
        }

        initializePhysics(false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ADesk d:desks) {
            if (d.getDeskType() == 0){
                d.paintDesk0(g2d);
            } else {
                d.paint(g2d);
            }
        }

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        for (DoArson arson: arson5){
            arson.paint(g2d, p1, p2, menu);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

        // physics should have access to if chalkboard and trash can are
        // REAL tasks

        g2d.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g2d.setColor(Color.white);
        g2d.drawString("the only room you're safe in:", 500, 50);
    }
}
