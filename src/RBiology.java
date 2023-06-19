import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RBiology {
    private boolean ts = true;

    private BufferedImage biodesk;
    private DoArson[] arson4 = new DoArson[3];
    private DoArson arsont1, arsont2, arsont3;
    private ATrashCan[] trashCans = new ATrashCan[3];
    private AChalkBoard cb;
    private AWall w1 = new AWall(0, 0);
    private ADesk[] desks = new ADesk[6];
    private DoDissection dissection4;
    private boolean b1;
    public ADesk[] getDesks(){
        return desks;
    }
    public DoDissection getDissection4(){
        return dissection4;
    }
    public DoArson[] getArson(){
        return arson4;
    }
    public ATrashCan[] getTrashCans(){
        return trashCans;
    }
    public AChalkBoard getCb(){
        return cb;
    }

    public void initializeBio(boolean b, boolean b1, boolean b2){
        ts = true;
        Random r = new Random();
        this.b1 = b1;

        trashCans[0] = new ATrashCan(r.nextInt(1001) + 400,r.nextInt(100) + 290); // top wall
        trashCans[1] = new ATrashCan(r.nextInt(271),r.nextInt(130) + 270); // left wall
        trashCans[2] = new ATrashCan(r.nextInt(221) + 1450,r.nextInt(451) + 300); //right wall

        arsont1 = new DoArson(b1, trashCans[0]);
        arsont2 = new DoArson(b1, trashCans[1]);
        arsont3 = new DoArson(b1, trashCans[2]);

        arson4[0] = arsont1;
        arson4[1] = arsont2;
        arson4[2] = arsont3;

        cb = new AChalkBoard(300, 10, "BIOLOGY",b);
        dissection4 = new DoDissection(b2);
    }
    public RBiology() {
        int counter = 0;

        //generating desks loop
        for (int i = 700; i <= 1000; i += 300){         // x
            for (int j = 500; j <= 800; j += 300){      // y
                desks[counter] = new ADesk(i, j);
                counter++;
            }
        }

        desks[4] = new ADesk(100, 520, 150, 400, true);
        desks[5] = new ADesk(400, 520, 150, 400, true);

        initializeBio(false, false, false);

        try {
            biodesk = ImageIO.read(new File("res\\biodesk.png"));
        } catch (IOException e) { System.out.println("frog no image"); }
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
                g2d.setColor(new Color(1, 1, 1, 0));
                d.paint(g2d);
            }
        }
        g2d.drawImage(biodesk, 100, 520, null);
        g2d.drawImage(biodesk, 400, 520, null);

        for (ATrashCan tc: trashCans){
            tc.paint(g2d, p1, p2, b1);
        }

        for (DoArson arson: arson4){
            arson.paint(g2d, p1, p2, menu);
        }

        if (ts && arsont1.getIsFin() && arsont2.getIsFin() && arsont3.getIsFin()){
            menu.updateTaskCompletion(0);
            ts = false;
        }


        w1.paint(g2d); // wall

        dissection4.paint(g2d, p1, p2, menu);

        cb.isPlayerClose(p1, p2);
        cb.paint(g2d, menu); // chalkboard

//        g2d.setColor(Color.black);
//        g2d.fillRect(400, 290, 1000, 130);   // top
//        g2d.fillRect(0, 270, 270, 150);     // left
//        g2d.fillRect(1450, 300, 220, 450);  // right
    }
}
