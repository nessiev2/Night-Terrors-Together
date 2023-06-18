import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ROffice {
    private DoArson[] arson1;
    private ATrashCan[] trashCans = new ATrashCan[1];
    //DoArson arson1 = new DoArson(false, trashCans);
    private AChalkBoard cb = new AChalkBoard(900, 10, "OFFICE", false);
    private ADesk[] desks = {new ADesk(0, 500, 500, 250), new ADesk(500, 300, 150, 250+200)};
    private AWall w1 = new AWall(0, 0);
    private APressurePlate pp = new APressurePlate(200, 300);
    private boolean b1;

    public ADesk[] getDesks(){
        return desks;
    }
    public APressurePlate getAPressurePlate(){
        return pp;
    }
    public DoArson[] getArson(){
        return arson1;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }

    public void initializeOffice(boolean b, boolean b1){
        Random r = new Random();
        this.b1 = b1;
        // trashCans[0] = new ATrashCan(r.nextInt(400) + 270,r.nextInt(91) + 290); // top wall
        // trashCans[1] = new ATrashCan(r.nextInt(980) + 715,r.nextInt(91) + 290); // top wall
        trashCans[0] = new ATrashCan(r.nextInt(221) + 1420,r.nextInt(221) + 660); //right wall
    }
    public ROffice(){
        initializeOffice(false, false);
    }
    public void paint(Graphics g, Player p1, Player p2, Transition transition1, SideMenu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        for (ADesk d:desks) {
            d.paint(g2d);
        }

        w1.paint(g2d); // wall

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

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
