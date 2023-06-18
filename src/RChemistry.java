import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class RChemistry {
    private DoArson[] arson6 = new DoArson[3];
    private DoArson arsont1, arsont2, arsont3;
    private ATrashCan[] trashCans = new ATrashCan[3];
    private AChalkBoard cb;
    private AWall w1 = new AWall(0, 0);
    private ADesk[] desks = {new ADesk(400, 500), new ADesk(800, 500), new ADesk(400, 800), new ADesk(800, 800), new ADesk(1200, 500, 500, 150), new ADesk(1200, 500, 500, 150)};
    private DoBadReaction badReaction6;
    boolean b1;
    public ADesk[] getDesks(){
        return desks;
    }
    public DoBadReaction getBadReaction6(){
        return badReaction6;
    }
    public DoArson[] getArson(){
        return arson6;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }

    public void initializeChem(boolean b, boolean b1, boolean b2){
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(1431) + 270,r.nextInt(91) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(221) + 660); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson6[0] = arsont1;
        arson6[1] = arsont2;
        arson6[2] = arsont3;

        cb = new AChalkBoard(300, 10, "CHEMISTRY", b);
        badReaction6 = new DoBadReaction(b2);
    }

    public RChemistry() {
        initializeChem(false, false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu, int currentClassroom) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        badReaction6.doTask(p1, p2);

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        for (DoArson arson: arson6){
            arson.paint(g2d, p1, p2, menu);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

        badReaction6.paint(g2d, p1, p2, menu, currentClassroom);
    }


}
