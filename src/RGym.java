import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class RGym {
    private DoArson[] arson3;
    private DoMess mess3;
    private ATrashCan[] trashCans = {};
    private AChalkBoard cb;
    private ADesk[] desks = {};
    private AWall w1 = new AWall(0, 0);
    private boolean b1;

    public ADesk[] getDesks(){
        return desks;
    }
    public DoMess getDoMess(){
        return mess3;
    }
    public DoArson[] getArson(){
        return arson3;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }

    public void initializeGym(boolean b, boolean b1, boolean b2){
        this.b1 = b1;
        mess3 = new DoMess(b2);
        cb = new AChalkBoard(500, 10, "GYM", b);
    }
    public RGym(){
        initializeGym(false, false, false);
    }

    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

        mess3.paint(g2d, menu);

        if (p1.getIsSpillingWater()){
            mess3.addWaterStain(p1, g2d);
        }
        if (p2.getIsSpillingWater()){
            mess3.addWaterStain(p2, g2d);
        }
    }
}
